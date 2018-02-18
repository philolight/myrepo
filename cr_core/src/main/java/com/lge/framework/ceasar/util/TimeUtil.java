/**
 * Copyright (C) 2017 LG Electronics Inc. All Rights Reserved.
 * Though every care has been taken to ensure the accuracy of this document,
 * LG Electronics Inc. cannot accept responsibility for any errors or
 * omissions or for any loss occurred to any person, whether legal or natural,
 * from acting, or refraining from action, as a result of the information
 * contained herein. Information in this document is subject to change at any
 * time without obligation to notify any person of such changes.
 * LG Electronics Inc. may have patents or patent pending applications,
 * trademarks copyrights or other intellectual property rights covering subject
 * matter in this document. The furnishing of this document does not give the
 * recipient or reader any license to these patents, trademarks copyrights or
 * other intellectual property rights.
 * No part of this document may be communicated, distributed, reproduced or
 * transmitted in any form or by any means, electronic or mechanical or
 * otherwise, for any purpose, without the prior written permission of
 * LG Electronics Inc.
 * The document is subject to revision without further notice.
 * All brand names and product names mentioned in this document are trademarks
 * or registered trademarks of their respective owners
 *
 * Author: eungsuk.shon
 */

package com.lge.framework.ceasar.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.lge.framework.ceasar.logger.Logger;
import com.lge.sm.cr_data_store.common.Constants;

public class TimeUtil {
	private static final String TAG = TimeUtil.class.getSimpleName();
	private static final String DATE_FORMAT = Constants.DEFAULT_DATE_FORMAT;
	public static final Date DEFAULT_DATE = new Date(0);
	
	public static String koreaTimeToLocal(String time, String timeZoneId, String format){
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = f.parse(time);
		} catch (ParseException e) {
			Logger.error(TAG, "Wrong time or time format : " + time + " " + format);
			return "";
		}

		f.applyPattern(DATE_FORMAT);
		f.setTimeZone(TimeZone.getTimeZone(timeZoneId));
		return f.format(date);
	}
	
	public static String getHHmm(String time, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		
		Date d = null;
		try {
			d = f.parse(time);
			f.applyPattern(DATE_FORMAT);
			String str = f.format(d);
			return str.substring(8, 12);
		} catch (ParseException e) {
			Logger.error(TAG, "Datetime string parsing failed");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String utcLongToString(long utcLong, String timeZoneId) {
		Date date = new Date(utcLong);

		SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT);

		TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
		f.setTimeZone(timeZone);
		return f.format(date);
	}
	
	private static SimpleDateFormat formatter = new SimpleDateFormat();

	public static String getFormattedDate(Date date, String format) {
		String ret = null;
		if (date != null && format != null) {
			synchronized (formatter) {
				formatter.applyPattern(format);
				ret = formatter.format(date);
			}
		}
		return ret;
	}
	
	public static Date getDateWithFormat(String dateString, String format) throws Exception {
		return getDateWithFormat(dateString, format, new ParsePosition(0));
	}

	public static Date getDateWithFormat(String dateString, String format, ParsePosition position) throws Exception {
		Date ret = null;
		if (dateString != null && format != null && position != null && position.getIndex() < dateString.length()) {
			synchronized (formatter) {
				formatter.applyPattern(format);
				ret = formatter.parse(dateString, position);
			}
		}
		return ret;
	}
	
	public static String dateToString(Date date, String timeZoneId, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);

		TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
		f.setTimeZone(timeZone);
		return f.format(date);
	}
}
