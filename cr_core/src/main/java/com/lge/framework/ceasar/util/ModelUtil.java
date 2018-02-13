package com.lge.framework.ceasar.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.UnsupportedDataTypeException;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.common.Constants;
import com.lge.sm.cr_data_store.dto.SlmDto;

public class ModelUtil {
	private static final String TAG = ModelUtil.class.getSimpleName();
	
	/** 두 오브젝트에 셋팅된 값이 같은지를 검사하여 같으면 true, 틀리면 false를 내보냄 */
	public static boolean isEqual(Object left, Object right){
		Field[] leftFields = left.getClass().getDeclaredFields();
		Field[] rightFields = right.getClass().getDeclaredFields();

		for(int i = 0; i < leftFields.length; i++){
			leftFields[i].setAccessible(true);
			rightFields[i].setAccessible(true);
			
			try{
				if(isEqualField(left, right, leftFields[i], rightFields[i]) == false) return false;
			} catch(Exception e){
				if(e.getCause() != null) Logger.error(TAG, "isEqual() failed : " + e.getCause().getMessage());
				else Logger.error(TAG, "isEqual() failed");
			}
		}
		
		return true;
	}

	private static boolean isEqualField(Object leftObject, Object rightObject, Field leftField, Field rightField) throws Exception{
		Object leftItem = leftField.get(leftObject);
		Object rightItem = rightField.get(rightObject);
		
		if(leftItem == null && rightItem == null) return true;
		if(leftItem == null || rightItem == null) return false;
		
		if(leftField.getType().isPrimitive()){
			return leftItem.equals(rightItem);
		}
		else if(leftItem instanceof Number || leftItem instanceof String || leftItem instanceof Boolean || leftItem instanceof BigDecimal){ // immutable
			return leftItem.equals(rightItem);
		}
		else if(leftField.getType().isArray()){
			if(leftField.getType().getComponentType().equals(rightField.getType().getComponentType()) == false) return false;
			if(Array.getLength(leftItem) != Array.getLength(rightItem)) return false;
			for(int i = 0; i < Array.getLength(leftItem); i++){
				Object leftElement = Array.get(leftItem, i);
				Object rightElement = Array.get(rightItem, i);
				if(leftElement == null && leftElement == null) continue;
				if(leftElement == null || leftElement == null) return false;
				
				if(leftElement.equals(rightElement) == false) return false;
			}
			return true;
		}
		else throw new UnsupportedDataTypeException();
	}
	
	public static boolean hasNullFields(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();

		for(Field each : fields){
			each.setAccessible(true);
			
			try{
				if(each.get(obj) == null) return true;
			} catch(Exception e){
				if(e.getCause() != null) Logger.error(TAG, "noNullFields() failed : " + e.getCause().getMessage());
				else Logger.error(TAG, "noNullFields() failed");
			}
		}
		
		return false;
	}
	
	public static String getSlmPath(SlmDto slm) {
		String ret = null;
		if (slm.getProtocol() != null && slm.getIp() != null && slm.getPort() > 0) {
			try {
				URL url = new URL(slm.getProtocol(), slm.getIp(), slm.getPort(), Constants.POINT_ACCESS_SERVICE_API_PATH);
				ret = url.toString();
			} catch (MalformedURLException e) {
				Logger.error(TAG, e.getMessage());
			}
		}
		return ret;
	}
}
