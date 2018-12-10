package com.ctfs.qloudMarket.market_service.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/7
 * Time: 10:05
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {
    public  static Logger log= LoggerFactory.getLogger(Logger.class);
    /**
     * 默认日期格式
     */
    public static final String DATESHOWFORMAT = "yyyy-MM-dd";
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATESHORTFORMAT = "yyyyMMdd";
    public static final String DATEMONTHFORMAT = "yyyyMM";
    public static final String YEARFORMAT = "yyyy";
    public static final String DATETIMELAYOUT = "yyyyMMddHHmmss";
    public static final String DATEFORMAT_ZH = "MM月dd日";
    public static final String TIME_FORMAT = "HH:mm:ss";


    /**
     * 得到指定月的最后一天的日期
     *
     * @param month yyyyMM   如：201309  返回  20130930
     * @return
     */
    public static String getLastDayFromMonth(String month) {
        String result = "";
        if (month.length() != 6) {
            return result;
        }
        DateTime dt = DateTime.parse(month, DateTimeFormat.forPattern(DATEMONTHFORMAT));
        result = month + "" + dt.dayOfMonth().getMaximumValue();
        return result;
    }

    /**
     * 获取短长度时间串
     * @return
     */
    public static String getShortCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        return format.format(date);
    }
    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getCurrentDate_format(String formatT) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatT);
        return format.format(date);
    }


    public static String getCurrentDate(String formatType) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        return format.format(date);
    }
    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDateFormat() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    /**
     * 得到指定月的第一天的日期
     *
     * @param month yyyyMM   如：201309  返回  20130901
     * @return
     */
    public static String getFirstDayFromMonth(String month) {
        String result = "";
        if (month.length() != 6) {
            return result;
        }
        DateTime dt = DateTime.parse(month, DateTimeFormat.forPattern(DATEMONTHFORMAT));
        result = month + "0" + dt.dayOfMonth().getMinimumValue();
        return result;
    }

    /**
     * 得到今天指定格式的日期
     *
     * @param format
     * @return
     */
    public static String getToDay(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }
        DateTime dt = new DateTime();
        return dt.toString(format);
    }

    /**
     * 得到指定格式
     *
     * @param format
     * @return
     */
    public static String getDateFormat(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }
        return format;
    }

    /**
     * 得到昨天的日期
     *
     * @return YYYYMMDD
     */
    public static String getYesterdayDate(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }
        return DateUtils.getAroundDateByDay(-1, DateUtils.getToDay(format), format);
    }

    /**
     * 得到指定日期的相隔日期
     *
     * @param cycleNum 相隔多少天
     * @param inDate   传入的时间，格式：yyyyMMdd
     * @param format   传出的时间格式
     * @return
     */
    public static String getAroundDateByDay(int cycleNum, String inDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }

        inDate = inDate.substring(0, 4) + "-" + inDate.substring(5, 7) + "-" + inDate.substring(8, 10);
        DateTime dt = new DateTime(inDate);
        DateTime dtime = dt.plusDays(cycleNum);
        return dtime.toString(format);
    }

    public static DateTime getDateTime(String inDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }

        return DateTime.parse(inDate, DateTimeFormat.forPattern(format));
    }

    /**
     * 得到指定格式
     *
     * @param date
     * @return
     */
    public static String getDate(String date) {
        if (StringUtils.isEmpty(date)) {
            date = DATESHOWFORMAT;
        }
        return date;
    }

    /**
     * 得到指定日期的相隔日期
     *
     * @param cycleNum 相隔多少小时
     * @param inDate   传入的时间，格式：yyyyMMdd
     * @param format   传出的时间格式
     * @return
     */
    public static String getAroundDateByHour(int cycleNum, String inDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }

        DateTime dt = new DateTime(Integer.parseInt(inDate.substring(0,4)), Integer.parseInt(inDate.substring(5, 7)), Integer.parseInt(inDate.substring(8, 10)), Integer.parseInt(inDate.substring(11, 13)), Integer.parseInt(inDate.substring(14, 16)), Integer.parseInt(inDate.substring(17, 19)), 0);
        DateTime dtime = dt.minusHours(cycleNum);
        return dtime.toString(format);
    }

    /**
     * 得到指定日期的相隔年数的日期
     *
     * @param cycleNum 相隔多少年
     * @param inDate   传入的时间，格式：yyyyMMdd
     * @param format   传出的时间格式
     * @return
     */
    public static String getAroundDateByYear(int cycleNum, String inDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }

        DateTime dt = new DateTime(Integer.parseInt(inDate.substring(0, 4)), Integer.parseInt(inDate.substring(4, 6)), Integer.parseInt(inDate.substring(6, 8)), 0, 0, 0, 0);
        DateTime dtime = dt.plusYears(cycleNum);

        return dtime.toString(format);
    }

    /**
     * 得到两个时间相隔的天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getAroundDate(String beginDate, String endDate) {
        int i = 0;
        i = Days.daysBetween(getDateTime(beginDate, DATESHORTFORMAT), getDateTime(endDate, DATESHORTFORMAT)).getDays();
        return i;
    }

    /**
     * 得到指定日期的相隔月数的日期
     *
     * @param cycleNum 相隔多少年
     * @param inDate   传入的时间，格式：yyyyMMdd
     * @param format   传出的时间格式
     * @return
     */
    public static String getAroundDateByMonth(int cycleNum, String inDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATESHORTFORMAT;
        }

        DateTime dt = new DateTime(Integer.parseInt(inDate.substring(0, 4)), Integer.parseInt(inDate.substring(4, 6)), Integer.parseInt(inDate.substring(6, 8)),
                Integer.parseInt(inDate.substring(8, 10)), Integer.parseInt(inDate.substring(10, 12)), Integer.parseInt(inDate.substring(12, 14)));
        DateTime dtime = dt.plusMonths(cycleNum);

        return dtime.toString(format);
    }

    /**
     * 得到两个时间相隔的秒
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Long getAroundMinute(String beginDate, String endDate) {
        long i = 0;
        i = Seconds.secondsBetween(getDateTime(beginDate, "yyyyMMddhhmmss"), getDateTime(endDate, "yyyyMMddhhmmss")).getSeconds();
        return i;
    }

    /**
     * 日期转换
     *
     * @param date
     * @param date 字符串日期格式
     * @return
     * @throws Exception
     */
    public static java.sql.Date convertSQLDate(String date) {
        try {
            if (date == null) {
                return null;
            }
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //return new java.sql.Date(sdf.parse(date).getTime());
            FastDateFormat formatDate = FastDateFormat.getInstance(DATESHORTFORMAT);
            return new java.sql.Date(((java.sql.Date) formatDate.parseObject(date)).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @param startDate 开始时间，如：2008-12-03 11:00:00
     * @param endDate   结束时间，如：2009-12-31 11:00:00
     * @return long 间隔天数(long)
     */
    public static long getBetweenDays(Date startDate, Date endDate) {
        if (endDate == null || startDate == null) {
            return -1;
        }
        Long days = endDate.getTime() - startDate.getTime();
        days = days / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 验证输入的文本信息日期是否合
     *
     * @param dateStr
     * @return
     */
    public static Date isDate(String dateStr) {
        String date_format_1 = "yyyy/MM/dd";
        String date_format_2 = "yyyy-MM-dd";
        String date_format_3 = "yyyyMMdd";
        String date_format_4 = "yyyy.MM.dd";
        String[] date_format = {date_format_1, date_format_2, date_format_3, date_format_4};
        for (int i = 0; i < date_format.length; i++) {
            Date tempDate = isDate(dateStr, date_format[i]);
            if (null != tempDate) {
                return tempDate;
            }
        }
        return null;
    }

    /**
     * 验证输入的文本信息日期是否合
     *
     * @param patternString
     * @return
     */
    public static Date isDate(String dateStr, String patternString) {
        if (StringUtils.isEmpty(patternString)) {
            patternString = DATESHORTFORMAT;
        }
        try {
            //SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
            //formatDate.setLenient(false);
            FastDateFormat formatDate = FastDateFormat.getInstance(patternString);
            ParsePosition pos = new ParsePosition(0);
            Date tempDate = (Date) formatDate.parseObject(dateStr, pos);
            tempDate.getTime();
            return tempDate;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * date 转String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String pareDate(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATESHORTFORMAT;
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * String 转date
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date pareDate(String dateStr, String pattern) {
        if (null == dateStr) {
            return null;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATESHORTFORMAT;
        }
        DateTime dt = DateTime.parse(dateStr, DateTimeFormat.forPattern(pattern));
        return dt.toDate();
    }

    /**
     * 格式化Date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date formatDate(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATESHORTFORMAT;
        }
        String dateStr = DateFormatUtils.format(date, pattern);
        return pareDate(dateStr, pattern);
    }


    /**
     * 当前月往前推
     *
     * @param date 日期
     * @return Date 日期
     */
    public static Date getLastMonth(Date date, int cycle) {
        DateTime datetime = new DateTime(date);
        return datetime.minusMonths(cycle).toDate();
    }

    /**
     * 判断是否是工作日的算法
     *
     * @param date 可以传入new Date作为当前时间
     * @return true: 是工作日（周一至周五） <br/>false: 非工作日
     * @author jwxa
     */
    public static boolean isWorkDay(Date date) {
        if (date == null) {
            return false;
        }
        //使用Calendar控件判断
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获取星期几 周日1 周一2 周二3 ....
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //如果周一至周五 判断时间
        if (dayOfWeek >= 2 && dayOfWeek <= 6) {
            return true;
        }
        return false;
    }


    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getDateTimelayout() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATETIMELAYOUT);
        return format.format(date);
    }

    /**
     * 获取当前时间
     *
     * @param date 当前时间
     * @return 当前时间
     */
    public static String getDateTimeLayout(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIMELAYOUT);
        return format.format(date);
    }

    /**
     * 时间减法运算
     *
     * @param date  时间
     * @param value 减去的值
     * @param digit 操作的值(年月日时分秒)
     * @return 减去的值
     */
    public static String subtractionTime(Date date, int value, int digit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期
        calendar.set(digit, calendar.get(digit) - value);//让日期加1
        System.out.println(calendar.get(Calendar.DATE));//加1之后的日期Top

        return getDateTimeLayout(calendar.getTime());
    }


    public static String getNowDateZeroPoint(Date date) {
        String nowDate = getNowDate(date);
        return nowDate + "000000";
    }

    public static String getNowDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATESHORTFORMAT);
        return format.format(date);
    }


    public static String farmatDate(Date date, String farmat) {
        SimpleDateFormat format = new SimpleDateFormat(farmat);
        return format.format(date);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyyMMdd HHmmss
     *
     * @param strDate
     * @return
     */
    public static String strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIMELAYOUT);
        SimpleDateFormat formatter1 = new SimpleDateFormat(DATETIMEFORMAT);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return formatter1.format(strtodate);
    }


    public static String strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATESHOWFORMAT);
        SimpleDateFormat formatter1 = new SimpleDateFormat(DATETIMEFORMAT);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter1.parse(strDate, pos);
        return formatter.format(strtodate);
    }

    public static String dateFrrmatStrToStr(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIMEFORMAT);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateStr, pos);
        SimpleDateFormat formatter1 = new SimpleDateFormat(DATETIMELAYOUT);
        return formatter1.format(strtodate);

    }

    public static String getDateNow(Date nowDate) {
        SimpleDateFormat formatter1 = new SimpleDateFormat(DATETIMELAYOUT);
        return formatter1.format(nowDate);
    }


    /**
     * 获取当前时间昨天整天的时间段
     */
    public static Date[] getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date[] listDate = new Date[2];
        listDate[0] = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        listDate[1] = calendar.getTime();
        return listDate;
    }

    /**
     * 获取当天整天的时间段
     */
    public static Date[] getFullDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date[] listDate = new Date[2];
        listDate[0] = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        listDate[1] = calendar.getTime();
        return listDate;
    }

    public static String getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat(DATETIMEFORMAT);
        Date dt = new Date(todayStart.getTime().getTime());
        String sDateTime = sdf.format(dt);
        return sDateTime;

    }

    public static String getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);

        SimpleDateFormat sdf = new SimpleDateFormat(DATETIMEFORMAT);
        Date dt = new Date(todayEnd.getTime().getTime());
        System.out.println(dt);
        String sDateTime = sdf.format(dt);
        System.out.println(sDateTime);
        return sDateTime;
    }

    /**
     * 获取当前时间（如果当前分钟大于等于30则设置当前时间分钟为30 否则为0分钟）的hour个小时
     *
     * @param date 当前时间
     * @param hour 当前时间的前后N个小时
     * @return 当前时间前后N个小时何当前时间
     */
    public static String[] getCurrTimeBefore(Date date, int hour) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar dar = Calendar.getInstance();
        dar.setTime(date);
        dar.add(Calendar.HOUR_OF_DAY, hour);
        dar.set(Calendar.MINUTE, dar.get(Calendar.MINUTE) >= 30 ? 30 : 00);
        dar.set(Calendar.SECOND, 0);


        Calendar darCurr = Calendar.getInstance();
        darCurr.setTime(date);
        System.out.println(darCurr.get(Calendar.MINUTE) >= 30 ? 30 : 00);
        darCurr.set(Calendar.MINUTE, darCurr.get(Calendar.MINUTE) >= 30 ? 30 : 00);
        darCurr.set(Calendar.SECOND, 0);
        String hourBefore = dft.format(darCurr.getTime());
        String currTime = dft.format(dar.getTime());


        return new String[]{hourBefore, currTime};

    }

    /**
     * 根据传入的时间返回，前一个小时的时间段。
     *
     * @param date 原始值
     * @return 时间段。
     */
    public static Date[] getLastHour(Date date) {
        Date list[] = new Date[2];
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        //当前时间前一个小时
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        //设置开始时间
        list[0] = calendar.getTime();
        //设置结束时间
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        list[1] = calendar.getTime();
        return list;
    }



    public static int compare_date_format(String format,String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {

                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(),exception);
        }
        return 0;
    }

    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {

                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(),exception);
        }
        return 0;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取date时间的之前之后多少分钟
     *
     * @param date    时间
     * @param minutes 之前之后多少分钟
     * @return 传入时间和之前之后多少时间 rt[1]=之前时间间隔，rt[0]=当前时间
     */
    public static String[] timeOfManyMinutes(Date date, int minutes) {

        String list[] = new String[2];
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat(DATETIMEFORMAT);
        //当前时间前一个小时
        list[0] = format.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, minutes);
        list[1] = format.format(calendar.getTime());
        return list;

    }

    /**
     * 获取date时间的之前或之后多少分钟的时间点间隔
     *
     * @param date    时间
     * @param minutes 之前之后多少分钟
     * @return 传入时间和之前之后多少时间
     */
    public static String[] timeOfManyMinutes(Date date, int minutes, int minutes2) {

        String list[] = new String[3];
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat(DATETIMEFORMAT);
        //当前时间前一个小时
        list[0] = format.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, minutes);
        list[1] = format.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, minutes2 - minutes);
        list[2] = format.format(calendar.getTime());
        return list;

    }

    public static Date parseDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIMELAYOUT);
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取当前日期的前一周时间，包括当天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static List<String> lastWeek(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        List dateList = new ArrayList<Date>();
        long day = 0L;
        while (day < 7) {
            Date calculateDay = new Date(date.getTime() - day * 24 * 3600 * 1000);
            day++;
            //currentTimeMillis()
            dateList.add(pareDate(calculateDay, pattern));
        }
        return dateList;
    }

    private static int formatQuartarMinute(int input, int offset) {
        int interval = Math.abs(60 / offset);
        int rt = 0;
        if (input == 0) {
            return 60 - offset;
        }
        for (int i = 0; i < interval; i++) {


            if (input <= (i + 1) * offset && input > i * offset) {
                rt = i * offset;
                break;
            }
        }

        return rt;
    }


    /**
     * 获取前或后n个小时的开始和结束时间点所组成的数组
     *
     * @param formatStr 传入日期格式
     * @param timeStr   日期时间字符串
     * @return
     */
    public static String getFirstSecondOfTomorrow(String formatStr, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = formatter.parseDateTime(timeStr);
        String resultTimeStr = dateTime.plusDays(1).withTime(0, 0, 0, 0).toString(formatStr);
        return resultTimeStr;
    }

    /**
     * 获取传入时间当天的开始时间
     *
     * @param formatStr 传入日期格式
     * @param timeStr   日期时间字符串
     * @return
     */
    public static String getStartOfToday(String formatStr, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = formatter.parseDateTime(timeStr);
        String resultTimeStr = dateTime.withTime(0, 0, 0, 0).toString(formatStr);
        return resultTimeStr;
    }

    /**
     * 按照指定格式转换日期
     *
     * @param dateStr 日期
     * @param format  日期格式
     * @return 日期
     */
    public static Date string2Date(String dateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = formatter.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    /**
     * 按照指定格式转换日期
     *
     * @param dateStr 日期
     * @return 日期
     */
    public static Date string2Date(String dateStr) {
        return string2Date(dateStr, DATETIMEFORMAT);
    }

    public static void main(String[] args) throws ParseException {
//
//		System.out.println(Arrays.toString(timeOfManyMinutes(new Date(), -10)));
//        System.out.print(lastWeek(new Date(),DATESHOWFORMAT));
//        System.out.println();
//        Date date = pareDate("2016-03-09", DATESHOWFORMAT);
//        System.out.println(lastWeek(new Date(), DATESHOWFORMAT));
//        String str = getFirstSecondOfTomorrow(DATETIMEFORMAT, "2016-03-29 21:34:00");
//        System.out.println(str);
//        System.out.println(DateUtils.getStartOfToday(DATETIMEFORMAT, "2016-03-29 21:34:00"));
        String aaa=DateUtils.getCurrentDateTime();
        System.out.println(aaa);
        System.out.println( aaa.substring(0, 4)+" "+aaa.substring(5, 7)+" "+ aaa.substring(8,10)+" "+aaa.substring(11, 13)+" "+ aaa.substring(14, 16)+" "+ aaa.substring(17, 19));
        System.out.println(DateUtils.getAroundDateByHour(1,DateUtils.getCurrentDateTime(),DateUtils.DATETIMEFORMAT));
    }
}
