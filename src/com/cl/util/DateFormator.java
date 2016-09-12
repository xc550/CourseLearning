package com.cl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormator {
	private static final String pattern = "yyyy-MM-dd hh:mm:ss";
	
	public static Calendar getDateByPattern(String time) {
		System.out.println("time to Calendar: " + time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static String getDateCalendarToString(Calendar c) {
		System.out.println("time to String: " + c.toString());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String time = "1970-01-01 AM 00:00";
		time = sdf.format(c.getTime());
		return time;
	}
}
