package com.lge.framework.ceasar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.common.Constants;

/**
 * TimeString format = "yyyyMMddHHmmss"
 */
public class DateStringUtil {
	private static final String TAG = TimeUtil.class.getSimpleName();
	private static final long ONE_DAY_MILLIS = (24L * 60L * 60L * 1000L);
	private static final String gmtTimeZoneId = "GMT";
	private static final TimeZone gmtTimeZone = TimeZone.getTimeZone(gmtTimeZoneId);
	private static final String DATE_FORMAT = Constants.DEFAULT_DATE_FORMAT;
		
	public static String getCurrentDateString(String timeZoneId) {
		Calendar targetCalendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
			
		return TimeUtil.utcLongToString(targetCalendar.getTime().getTime(), timeZoneId);
	}
	
	public static String getPreviousDate(String dateString) {
		long utcLong = stringToUtcLong(dateString, DATE_FORMAT);
		utcLong -= ONE_DAY_MILLIS;
		
		return TimeUtil.utcLongToString(utcLong, gmtTimeZoneId);
	}
	
	public static String getNextDate(String dateString) {
		if(dateString == null) return null;
		long utcLong = stringToUtcLong(dateString, DATE_FORMAT);
		utcLong += ONE_DAY_MILLIS;
		
		return TimeUtil.utcLongToString(utcLong, gmtTimeZoneId);
	}
	
	public static String getStartDate(String dateString) {
		if(dateString == null) return null;
		String ret = dateString.substring(0, dateString.length()-6);
		return ret + "000000";
	}
	
	public static String getEndDate(String dateString) {
		if(dateString == null) return null;
		String ret = dateString.substring(0, dateString.length()-6);
		return ret + "235959";
	}
		
	private static long stringToUtcLong(String time, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		f.setTimeZone(gmtTimeZone);
		Date d = null;
		try {
			d = f.parse(time);
			return d.getTime();
		} catch (ParseException e) {
			Logger.error(TAG, "Datetime string parsing failed");
			e.printStackTrace();
		}

		return 0; // error
	}
}
