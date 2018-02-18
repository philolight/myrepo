package com.lge.sm.cr_data_store.spring_config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lge.framework.ceasar.logger.Logger;
import com.lge.framework.ceasar.util.JsonUtil;

@Repository
public class ChartDao {
	private static final String TAG = ChartDao.class.getSimpleName();
	
	public ChartDao() {
		System.out.println("ChartDao created");
	}
	@Autowired DataSource dataSource;
	
	public String getChartString(String skinType, Map<String, List<String>> legend, Map<String, String> axis, List<String> series, int limit) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			stmt = null;
			
			String sql = makeSql(skinType, legend, axis, series, limit);
			System.out.println("sql = " + sql);
			
			stmt = con.prepareStatement(sql);
			
			// parse result			
			List<String> legendStrings = new ArrayList<>();			
			for(String key : legend.keySet()) {
				legendStrings.add(key);
			}

			Map<String/*legend value*/, List<String>> result = new HashMap<>();
			
			List<String> xAxis = new ArrayList<>(); // x 축
			String axisString = axis.get("fieldName");
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				String x = rs.getString(dbName(axisString));
				if(x == null) continue;
				if(xAxis.size() == 0 || x.equals(xAxis.get(xAxis.size()-1)) == false) xAxis.add(x);
				
				for(int j = 0; j < rsmd.getColumnCount(); j++) {
					System.out.print(rs.getString(j + 1) + " ");
				}
				System.out.println();
				
				for(String each : legendStrings) { // Legend 중심으로 정리하는 경우
					List<String> seriesList = result.get(rs.getString(dbName(each)));
					if(seriesList == null) {
						seriesList = new ArrayList<>();
						result.put(rs.getString(dbName(each)), seriesList);
					}
					
					String value = rs.getString(dbName(series.get(0)));
					while(seriesList.size() < xAxis.size()-1) seriesList.add(null);
					if(seriesList.size() < xAxis.size()) seriesList.add(value);
					else {
						try
						{
						  double v = Double.parseDouble(seriesList.get(seriesList.size()-1)) + Double.parseDouble(value);
						  String strValue = (v == (long) v) ? String.format("%d",(long)v) : String.format("%s",v);						  
						  seriesList.set(seriesList.size()-1, strValue);
						}
						catch(NumberFormatException e)
						{
							seriesList.set(seriesList.size()-1, value);
						}
					}
				}

				if(legendStrings.size() == 1 && legend.get(legendStrings.get(0)).size() == 1) {
					for(String each : series) { // Series 중심으로 정리하는 경우
						List<String> seriesList = result.get(each);
						if(seriesList == null) {
							seriesList = new ArrayList<>();
							result.put(each, seriesList);
						}
						
						String value = rs.getString(dbName(each));
						while(seriesList.size() < xAxis.size()-1) seriesList.add(null);
						seriesList.add(value);
					}
				}
			}
			
			result.put("<axis>", xAxis);
			
			for(List<String> each : result.values()) {
				while(each.size() < xAxis.size()) each.add(null);
				for(int i = 0; i < each.size() / 2; i++) {
					String tmp = each.get(i);
					each.set(i, each.get(each.size() - 1 - i));
					each.set(each.size() - 1 - i, tmp);
				}
			}
			
			ObjectMapper mapper = JsonUtil.objectMapper();
			System.out.println("result = " + mapper.writeValueAsString(result));
			return mapper.writeValueAsString(result);
			
		} catch (SQLException e) {
			Logger.error(TAG, "Failed to read chart : SQLException : " + e.getMessage());
			return "";
		} catch (JsonProcessingException e) {
			Logger.error(TAG, "Failed to read chart : JsonProcessingException : " + e.getMessage());
			return "";
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				Logger.error(TAG, "Failed to close JDBC : " + e.getMessage());
			}
		}
	}
	
	private String makeSql(String tableName, Map<String, List<String>> legend, Map<String, String> axis, List<String> series, int limit) {
		String sql = "SELECT ";
		
		sql += dbName(axis.get("fieldName")); // X축 필드
		
		for(String key : legend.keySet()) { // 사용자가 선택한 Legend 이름이 있으면 검색 대상으로 넣는다.
			sql += "," + dbName(key);
		}
		
		for(String value : series) // 값 필드를 검색 대상으로 넣는다.
			sql += "," + dbName(value);
		
		sql += " FROM " + dbName(tableName) + " ";
		
		String where = "";
		for(String key : legend.keySet()) { // Legend가 지정되어 있을 경우 이를 검색 제약 조건으로 넣는다.
			List<String> list = legend.get(key);
			for(int i = 0; i < list.size(); i++) {
				if(where.length() == 0) where += "(";
				else where += "OR ";
				where += dbName(key) + " = '" + list.get(i) + "'";
			}
		}
		if(where.length() != 0) where += ") ";
		
		if(axis.get("from") != "") {
			if(where.length() != 0) where += "AND ";
			where += dbName(axis.get("fieldName")) + " >= '" + axis.get("from") + "' "; // X축 필드의 시작이 지정되어 있으면 X축 값이 시작값 이상이어야 한다.
		}
		if(axis.get("to") != "") {
			if(where.length() != 0) where += "AND ";
			where += dbName(axis.get("fieldName")) + " <= '" + axis.get("to") + "' ";	// X축 필드의 끝이 지정되어 있으면 X축 값이 끝값 이하여야 한다.
		}
		
		if(where != "") sql += "WHERE (" + where + ") ";
		
		sql += "ORDER BY " + dbName(axis.get("fieldName")) + " "; // 정렬 기준을 X축 필드로 설정한다. 내림차순으로 검색되기 때문에 결과를 제공할 때는 역순으로 정렬해야 한다.
							
		sql += " DESC LIMIT " + limit + ";";
		
		return sql;
	}
	
	public static String bigName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String smallName(String name) {
		if(name == null) return "";
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
	
	public static String dbName(String name) {
		String smallName = smallName(name);
		String lowerCaseName = smallName.toLowerCase();
		
		String ret = "";
		
		for(int i = 0; i < smallName.length(); i++) {
			if(smallName.charAt(i) == lowerCaseName.charAt(i)) ret += lowerCaseName.charAt(i);
			else ret += "_" + lowerCaseName.charAt(i);
		}
		
		return ret;
	}
}
