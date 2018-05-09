package com.tuyongkang.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具
 */
public class DateUtil {

    private DateUtil() {
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

    public static String getStringDate() {
        return dateFormat.format(new Date());
    }

    public static String getStringDateTime() {
        return dateTimeFormat.format(new Date());
    }

    public static String getStringTime() {
        return timeFormat.format(new Date());
    }

    public static Date parse(String dateStr, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    public static Date getCurrDate() {
        return new Date();
    }

    public static String getFormatDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getFormatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getCurrentDate() {
        String format = "yyyy-MM-dd";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = DATETIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    public static Date getWeekDate(Integer wk) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, wk);
        if (wk == Calendar.SUNDAY) {
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            cal.add(Calendar.DATE, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 获取本周第一天日期
     *
     * @return
     */
    public static String getWeekFirst() {
        return dateFormat.format(getWeekDate(Calendar.MONDAY));
    }

    /**
     * 获取本周最后一天日期
     *
     * @return
     */
    public static String getWeekLast() {
        return dateFormat.format(getWeekDate(Calendar.SUNDAY));
    }

    public static Date getMonthDate(Integer d) {
        Calendar cal = Calendar.getInstance();
        if (d == 0)
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        else if (d == 1) {
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            cal.add(Calendar.DATE, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 获取本月第一天日期
     *
     * @return
     */
    public static String getMonthFirst() {
        return dateFormat.format(getMonthDate(0));
    }

    /**
     * 获取本月最后一天日期
     *
     * @return
     */
    public static String getMonthLast() {
        return dateFormat.format(getMonthDate(1));
    }

    public static String getEnd() {
        Date d = dateAddDay(new Date(), 1);
        return getFormatDate(d, DATE_FORMAT);
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    public static String getCurrentTime() {
        String format = "yyyyMMddHHmmss";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = DATETIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long getMiniutes(String start, String end) throws ParseException {
        Date s = parse(start, DATETIME_FORMAT);
        Date e = parse(end, DATETIME_FORMAT);
        Long stamp = e.getTime() - s.getTime();
        return Math.round(Math.floor(stamp / 1000 / 60));
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getStringDate());
        System.out.println(getEnd());
        System.out.println(getWeekFirst());
        System.out.println(getWeekLast());
        System.out.println(getMonthFirst());
        System.out.println(getMonthLast());

        System.out.println(Math.round(getMiniutes("2017-06-03 17:35:45", "2017-06-03 17:39:55")));
    }

}
