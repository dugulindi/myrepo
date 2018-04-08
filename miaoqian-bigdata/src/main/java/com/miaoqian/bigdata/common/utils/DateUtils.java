package com.miaoqian.bigdata.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tom
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    private final StringBuffer buffer = new StringBuffer();
    private static final String ZERO = "0";
    private static DateUtils date;
    private static String LOG_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static SimpleDateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private static SimpleDateFormat simpeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat yyyymmddFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final int para = 10;

    public final String getNowString() {
        Calendar calendar = getCalendar();
        buffer.delete(0, buffer.capacity());
        buffer.append(getYear(calendar));

        if (getMonth(calendar) < para) {
            buffer.append(ZERO);
        }
        buffer.append(getMonth(calendar));

        if (getDate(calendar) < para) {
            buffer.append(ZERO);
        }
        buffer.append(getDate(calendar));
        if (getHour(calendar) < para) {
            buffer.append(ZERO);
        }
        buffer.append(getHour(calendar));
        if (getMinute(calendar) < para) {
            buffer.append(ZERO);
        }
        buffer.append(getMinute(calendar));
        if (getSecond(calendar) < para) {
            buffer.append(ZERO);
        }
        buffer.append(getSecond(calendar));
        return buffer.toString();
    }

    private static int getDateField(Date date, int field) {
        Calendar c = getCalendar();
        c.setTime(date);
        return c.get(field);
    }

    public static int getYearsBetweenDate(Date begin, Date end) {
        int bYear = getDateField(begin, Calendar.YEAR);
        int eYear = getDateField(end, Calendar.YEAR);
        return eYear - bYear;
    }

    public static int getMonthsBetweenDate(Date begin, Date end) {
        int bMonth = getDateField(begin, Calendar.MONTH);
        int eMonth = getDateField(end, Calendar.MONTH);
        return eMonth - bMonth;
    }

    public static int getWeeksBetweenDate(Date begin, Date end) {
        int bWeek = getDateField(begin, Calendar.WEEK_OF_YEAR);
        int eWeek = getDateField(end, Calendar.WEEK_OF_YEAR);
        return eWeek - bWeek;
    }

    public static int getDaysBetweenDate(Date begin, Date end) {
        int bDay = getDateField(begin, Calendar.DAY_OF_YEAR);
        int eDay = getDateField(end, Calendar.DAY_OF_YEAR);
        return eDay - bDay;
    }

    public static int getMinutesBetweenDate(Date begin, Date end) {
        int bMinute = getDateField(begin, Calendar.MINUTE);
        int eMinute = getDateField(end, Calendar.MINUTE);
        return eMinute - bMinute;
    }

    public static int getSecondBetweenDate(Date begin, Date end) {
        int bSecond = (int) begin.getTime()/1000;
        int eSecond = (int) end.getTime()/1000;
        return eSecond - bSecond;
    }

    /**
     * 获取date年后的amount年的第一天的开始时间
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficYearStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, amount);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取date年后的amount年的最后一天的终止时间
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficYearEnd(Date date, int amount) {
        Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
        Calendar cal = Calendar.getInstance();
        cal.setTime(temp);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getFinallyDate(cal.getTime());
    }

    /**
     * 获取date月后的amount月的第一天的开始时间
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficMonthStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取当前自然月后的amount月的最后一天的终止时间
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficMonthEnd(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getSpecficMonthStart(date, amount + 1));
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getFinallyDate(cal.getTime());
    }

    /**
     * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficWeekStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
        cal.add(Calendar.WEEK_OF_MONTH, amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天）
     *
     * @param amount 可正、可负
     * @return
     */
    public static Date getSpecficWeekEnd(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
        cal.add(Calendar.WEEK_OF_MONTH, amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getFinallyDate(cal.getTime());
    }

    public static Date getSpecficDateStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, amount);
        return getStartDate(cal.getTime());
    }

    /**
     * 得到指定日期的一天的的最后时刻23:59:59
     *
     * @param date
     * @return
     */
    public static Date getFinallyDate(Date date) {
        String temp = format.format(date);
        temp += " 23:59:59";

        try {
            return format1.parse(temp);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到指定日期的一天的开始时刻00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        String temp = format.format(date);
        temp += " 00:00:00";

        try {
            return format1.parse(temp);
        } catch (Exception e) {
            return null;
        }
    }

    private int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    private int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONDAY) + 1;
    }

    private int getDate(Calendar calendar) {
        return calendar.get(Calendar.DATE);
    }

    private int getHour(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private int getMinute(Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    private int getSecond(Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static DateUtils getDateInstance() {
        if (date == null) {
            date = new DateUtils();
        }
        return date;
    }

    public static Timestamp getNowTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static SimpleDateFormat getFormat() {
        return format;
    }

    public static void setFormat(SimpleDateFormat format) {
        DateUtils.format = format;
    }

    public static SimpleDateFormat getFormat1() {
        return format1;
    }

    public static void setFormat1(SimpleDateFormat format1) {
        DateUtils.format1 = format1;
    }

    public static SimpleDateFormat getSimpeFormat() {
        return simpeFormat;
    }

    public static void setSimpeFormat(SimpleDateFormat simpeFormat) {
        DateUtils.simpeFormat = simpeFormat;
    }

    public static SimpleDateFormat getYyyymmddFormat() {
        return yyyymmddFormat;
    }

    public static void setYyyymmddFormat(SimpleDateFormat yyyymmddFormat) {
        DateUtils.yyyymmddFormat = yyyymmddFormat;
    }

    public static String getLogDateFormat() {
        return LOG_DATE_FORMAT;
    }

    public static SimpleDateFormat getLogFormat() {
        return logFormat;
    }

    public static void setLogFormat(SimpleDateFormat logFormat) {
        DateUtils.logFormat = logFormat;
    }

    public static void main(String[] args) throws ParseException {
        Date date1 = yyyymmddFormat.parse("2014-02-18");
        Date date2 = yyyymmddFormat.parse("2017-03-29");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        System.out.println(between_days);
        for (int i=0;i<=between_days;i++) {
            System.out.println(yyyymmddFormat.format(addDays(date1,i)));
        }
    }

}
