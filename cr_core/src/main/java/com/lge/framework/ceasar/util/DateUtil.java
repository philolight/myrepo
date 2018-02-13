package com.lge.framework.ceasar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lge.framework.pacific.logger.Logger;

public class DateUtil {
	private static final String TAG = TimeUtil.class.getSimpleName();
	private static final long ONE_DAY_MILLIS = (24L * 60L * 60L * 1000L);
	
	public static Date getCurrentDate() { return new Date(); }
	
	public static Date getPreviousDate(Date date) {
		return new Date(date.getTime() - ONE_DAY_MILLIS);
	}
	
	public static Date stringToDate(String time, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = f.parse(time);
			return d;
		} catch (ParseException e) {
			Logger.error(TAG, "Datetime string parsing failed");
			e.printStackTrace();
		}

		return null; // error
	}
}
