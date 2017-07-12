package com.healthcare.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Immutable Class for some date utils methods
 * @author Anass
 *
 */
public final class DateUtils {
	
	public static final  String DAY_FORMAT = "EEEE";
	
	public static final String US_LOCAL = "US";
	
	/**
	 * date format yyyy-MM-dd
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * days delimiter
	 */
	public static final String DAYS_DELIMITER = ",";
	/**
	 * constructor
	 */
	private DateUtils(){};
	
	/**
	 * format date to String
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String formatString(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * generate service calendar
	 * @param startdate
	 * @param enddate
	 * @return List<Date>
	 */
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate, String SchudeledDays)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);
	    String[] splitedSchudeledDays = SchudeledDays.split(DAYS_DELIMITER);
	    List<String> scheduledTableDates = Arrays.asList(splitedSchudeledDays);
	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        if(scheduledTableDates.contains(new SimpleDateFormat(DAY_FORMAT, new Locale(US_LOCAL)).format(result).toUpperCase()))
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	public static String getCurrentDate(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}

	public static Date stringToDate(String format, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}

