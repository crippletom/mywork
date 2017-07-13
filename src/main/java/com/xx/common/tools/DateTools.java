package com.xx.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DateTools {
	
	/** 锁对象 */
	private static final Object lockObj = new Object();

	/**
	 * 存放不同格式的线程安全的SimpleDateFormat
	 */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					tl = new ThreadLocal<SimpleDateFormat>() {
						@Override
						protected SimpleDateFormat initialValue() {
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}
		return tl.get();
	}

	
	/**
	 * 日期格式，正则表达式
	 */
	private static String[] supportFmtPattern = new String[] { 
			"\\d{4}\\-\\d{1,2}\\-\\d{1,2}", "yyyy-MM-dd", 
			"\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}", "yyyy-MM-dd HH:mm:ss", 
			"\\d{1,2}:\\d{1,2}:\\d{1,2}", "HH:mm:ss", 
			"\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}", "HH:mm:ss.SSS", 
			"\\d{4}年\\d{1,2}月\\d{1,2}日", "yyyy年MM月dd日",
			"\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}", "yyyy-MM-dd HH:mm:ss.SSS", 
			"\\d{4}(10|11|12|0[1-9])(30|31|[1-2][0-9]|0[1-9])(20|21|22|23|1[0-9]|0[0-9])([1-5][0-9]|0[0-9])([1-5][0-9]|0[0-9])", "yyyyMMddHHmmss", 
			"\\d{4}(10|11|12|0[1-9])(30|31|[1-2][0-9]|0[1-9])" , "yyyyMMdd",
			"\\d{4}(10|11|12|0[1-9])(30|31|[1-2][0-9]|0[1-9])(20|21|22|23|1[0-9]|0[0-9])([1-5][0-9]|0[0-9])([1-5][0-9]|0[0-9])\\d{1,3}", "yyyyMMddHHmmssSSS", 
			"\\d{4}(10|11|12|0[1-9])", "yyyyMM",
			"\\d{4}\\/\\d{1,2}\\/\\d{1,2}", "yyyy/MM/dd",
			"\\d{4}\\.\\d{1,2}\\.\\d{1,2}", "yyyy.MM.dd",
			"\\d{1,2}\\-\\d{1,2}\\-\\d{4}", "MM-dd-yyyy", 
			"\\d{1,2}\\/\\d{1,2}\\/\\d{4}", "MM/dd/yyyy",
			"\\d{1,2}\\/\\d{1,2}\\/\\d{2}", "MM/dd/yy",
			"\\d{4}\\/\\d{1,2}\\/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}", "yyyy/MM/dd'T'HH:mm:ss", 
			"\\d{4}\\/\\d{1,2}\\/\\d{1,2}t\\d{1,2}:\\d{1,2}:\\d{1,2}", "yyyy/MM/dd't'HH:mm:ss", 
			"\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}", "yyyy-MM-dd'T'HH:mm:ss", 
			"\\d{4}-\\d{1,2}-\\d{1,2}t\\d{1,2}:\\d{1,2}:\\d{1,2}", "yyyy-MM-dd't'HH:mm:ss",
			"\\d{4}\\/\\d{1,2}\\/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z", "yyyy/MM/dd'T'HH:mm:ss.SSS'Z'", 
			"\\d{4}\\/\\d{1,2}\\/\\d{1,2}t\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}z", "yyyy/MM/dd't'HH:mm:ss.SSS'z'", 
			"\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", 
			"\\d{4}-\\d{1,2}-\\d{1,2}t\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}z", "yyyy-MM-dd't'HH:mm:ss.SSS'z'"
			};
	/**
	 * 根据日期时间串推断日期格式
	 * @param datestr
	 * @return
	 */
	public static String checkFmtByPattern(String datestr) {
		if (datestr == null)
			return null;
		for(int i=0; i< supportFmtPattern.length; i+=2) {
			if (Pattern.matches(supportFmtPattern[i], datestr)) {
				return supportFmtPattern[i+1];
			};
		}
		return null;
	}

	/**
	 * 根据字符串格式自动判断日期格式，支持11种  <br>
	 * yyyy-MM-dd HH:mm:ss.SSS <br>
	 * yyyy-MM-dd HH:mm:ss <br>
	 * yyyy-MM-dd <br>
	 * yyyy.MM.dd <br>
	 * yyyy/MM/dd <br>
	 * HH:mm:ss.SSS <br>
	 * HH:mm:ss <br>
	 * yyyyMMdd <br>
	 * yyyyMMddHHmmssSSS <br>
	 * yyyyMMddHHmmss <br>
	 * yyyyMM <br>
	 * 
	 * @param datestr
	 * @return
	 */
	public static Date parse(String datestr) {
		String fmt = checkFmtByPattern(datestr);
		return parse(datestr, fmt);
	}

	/**
	 * parse 日期  <br>
	 * @param datestr
	 * @param fmt
	 * @return
	 */
	public static Date parse(String datestr, String fmt) {
		try {
			return getSdf(fmt).parse(datestr);
		} catch (NullPointerException e) {
			//TODO
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			//TODO
			e.printStackTrace();
			return null;
		}

	}
	

	/**
	 * 将日期格式化成指定格式
	 * 
	 * @param dt
	 * @param pattern
	 * @return
	 */
	public static String format(Date dt, String pattern) {
		return getSdf(pattern).format(dt);
	}


	/**
	 * 计算月份差(日期型)，按月份计算忽略天数
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static Integer monthBetween(Date fromDate, Date toDate) {

		if ((fromDate == null) || (toDate == null)) {
			return null;
		}
		int times = 1;
		if (!fromDate.after(toDate)) {
			Calendar calfrom = Calendar.getInstance();
			Calendar calto = Calendar.getInstance();
			calfrom.setTime(fromDate);
			calfrom.set(Calendar.DAY_OF_MONTH, 1);
			calto.setTime(toDate);
			calto.set(Calendar.DAY_OF_MONTH, 1);
			while (calfrom.before(calto)) {
				calfrom.add(2, 1);
				times++;
			}
		} else {
			return 0;
		}
		return new Integer(times);

	}

	/**
	 * 计算月份差(字符型)，自动判定格式
	 * 
	 * @param fromDateD
	 * @param toDateD
	 * @return
	 */
	public static Integer monthBetween(String fromDate, String toDate) {

		if ((fromDate == null) || (toDate == null)) {
			return null;
		}
		return monthBetween(parse(fromDate), parse(toDate));

	}

	/**
	 * 计算天数差
	 * 
	 * @param from
	 * @param end
	 * @return
	 */
	public static Integer daysBetween(Date from, Date end) {
		if ((from == null) || (end == null)) {
			return null;
		}
		long fromL = from.getTime();
		long endL = end.getTime();
		double diff = (endL - fromL) / 86400000L;
		return new Integer(new Double(Math.ceil(diff)).intValue());
	}

	/**
	 * 计算天数差
	 * 
	 * @param from
	 * @param end
	 * @return
	 */
	public static Integer dayBetween(String from, String end) {
		if ((from == null) || (end == null)) {
			return null;
		}
		return daysBetween(parse(from), parse(end));
	}
	
	
	/**
	 * 日期加减 年 月日
	 * @param date
	 * @param adds
	 * @return
	 */
	public static Date addDay(Date date, int adds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, adds);
		return cal.getTime();
	}

	public static String addDay(String date, int adds) {
		String fmt = checkFmtByPattern(date);
		Date r = addDay(parse(date), adds);
		return format(r, fmt);
	}
	
	public static Date addMonth(Date date, int adds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, adds);
		return cal.getTime();
	}

	public static String addMonth(String date, int adds) {
		String fmt = checkFmtByPattern(date);
		Date r = addMonth(parse(date), adds);
		return format(r, fmt);
	}
	
	public static Date addYear(Date date, int adds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, adds);
		return cal.getTime();
	}

	public static String addYear(String date, int adds) {
		String fmt = checkFmtByPattern(date);
		Date r = addYear(parse(date), adds);
		return format(r, fmt);
	}
}
