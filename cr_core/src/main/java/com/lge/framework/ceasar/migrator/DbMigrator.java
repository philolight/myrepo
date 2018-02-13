package com.lge.framework.ceasar.migrator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lge.framework.ceasar.csv.CsvLoader;

public class DbMigrator extends CsvLoader{
	private List<String> array = Arrays.asList("schedule_id",
	"room_id",
	"name",
	"user_id",
	"user_name",
	"dept_name",
	"sdate",
	"edate",
	"local_year",
	"local_month",
	"local_day",
	"local_day_of_week",
	"local_shhmm",
	"local_ehhmm",
	"local_duration",
	"sensor_cnt",
	"total_sensor",
	"total_detect",
	"chk_duration",
	"result",
	"cdate",
	"location_id",
	"error_cnt");
	
	
	@Override
	protected void processFieldMap(Map<String, String> pairMap) throws Exception {
		String q = "";
		
		for(String key : pairMap.keySet()) {
			boolean found = false;
			for(String each : array) {
				if(each.equals(key)) {
					found = true;
					break;
				}
			}
			if(found == false) {
				pairMap.put("schedule_id", pairMap.get(key));
				break;
			}
		}
		
		q = "insert into schedule(";
		for(String key : array) q = q + key +",";
		q = q + "result_date";
		q = q + ") values(";
		for(String key : array) {
//			if(key == "schedule_id" && pairMap.get(key) == null) System.out.println("error");
			q = q + "'" + pairMap.get(key) + "'" + ",";
		}
		q = q + "'" + pairMap.get("sdate") + "'";
		q = q + ");";
		
		System.out.println(q);
		
		pairMap.remove("schedule_id");
	}

	public static void main(String[] args) {
		DbMigrator m = new DbMigrator();
		try {
			m.load("C://Users/USER/schedule.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
