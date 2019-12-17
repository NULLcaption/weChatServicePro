package com.cxg.weChat.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Administrator on 2019/4/26.
 */

public class DateUtils {
    //日期的正则表达式：匹配 2004-04
    public static String DATE_PATTERN_MONTH = "^[0-9]{4}-(0[1-9]|1[0-2])$";

    //日期的正则表达式：匹配 2004-04-30 | 2004-02-29
    public static String DATE_PATTERN = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

    /**
     * 修改日期的时间
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date replaceTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获取当前时间转换为固定格式
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    public static String dateToStrYYYYMMDD(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(new Date());
    }

    /**
     * 修改日期的某一部分
     * @param date
     * @param field Calendar.Type的一个值
     * @param value
     * @return
     */
    public static Date dateReplace(Date date, int field, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(field, value);
        return cal.getTime();
    }

    public static Date strToDateForTime(String s) throws Exception {
        if (s == null || s.equals("")) return null;
        String[] dfs = new String[]{"yyyy-MM-dd", "yyyy-MM-dd", "yyyy年MM月dd日",
                "yyyy-M-d", "yyyy/M/d", "yyyy年M月d日"};
        for (String df: dfs) {
            try {
                DateFormat dd = new SimpleDateFormat(df);
                return dd.parse(s);
            } catch (Exception e) {
                continue;
            }
        }
        throw new Exception("日期格式不对，识别格式为"
                + "2018-01-01,2018/01/01,2018年01月01日,"
                + "2018-1-1,2018/1/1,2018年1月1日"
                + "等格式");
    }

    /**
     * 字符串转化为日期
     * @param s
     * @return
     * @throws Exception
     */
    public static Date strToDate(String s) throws Exception {
        if (s == null || s.equals("")) return null;
        String[] dfs = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH:mm:ss",
                "yyyy-M-d HH:mm:ss", "yyyy/M/d HH:mm:ss", "yyyy年M月d日 HH:mm:ss"};
        for (String df: dfs) {
            try {
                DateFormat dd = new SimpleDateFormat(df);
                return dd.parse(s);
            } catch (Exception e) {
                continue;
            }
        }
        throw new Exception("日期格式不对，识别格式为"
                + "2018-01-01,2018/01/01,2018年01月01日,"
                + "2018-1-1,2018/1/1,2018年1月1日"
                + "等格式");
    }

    /**
     * 字符串转化为日期
     * @param s
     * @param dateFormat
     * @return
     */
//    public static Date strToDate(String s, String dateFormat) {
//        DateFormat dd = new SimpleDateFormat(dateFormat);
//        try {
//            if(FUtils.isNumeric(s)&&s.length()==8){
//                s=s.substring(0, 4)+"-"+s.substring(4,6)+"-"+s.substring(6,8);
//            }
//            return dd.parse(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 当年的第一天
     * @param date
     * @return
     */
    public static Date getYearFirst(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    //当天的起始时间
    public static Date getDayFirst(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //当天的结束时间
    public static Date getDayLast(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    //当月的第一天
    public static Date getMonthFirst(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 当年的最后一天
     * @param date
     * @return
     */
    public static Date getYearLast(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        return cal.getTime();
    }

    //当月的最后一天
    public static Date getMonthLast(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getTime();
    }

    //当前时间所在月份的天数
    public static int getMonthDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.get(Calendar.DATE);
    }

    //当前时间获取上个月的时间
    public static Date getSameMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    //当前时间获取上年的时间
    public static Date getSameYearDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 获取两个时间之间间隔月份
     * 小的时间在前，大的在后
     * 不足月，返回1
     * @param smallDate
     * @param bigDate
     * @return
     */
    public static int dateDiffMonth(Date smallDate, Date bigDate) {
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        startCal.setTime(smallDate);
        endCal.setTime(bigDate);
        int m = 0;

        while (!startCal.after(endCal)) {
            startCal.add(Calendar.MONTH, 1);
            m++;
        }

        return m;
    }

    /**
     * 增加时间
     * @param date
     * @param calendarType 如： Calendar.MONTH
     * @param i
     * @return
     */
    public static Date dateAdd(Date date, int calendarType, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(calendarType, i);
        return cal.getTime();
    }

    /**
     * 获取两个时间之间的间隔天数
     * 不足一天也返回1
     * @param date1  小的日期
     * @param date2  大的日期
     * @return
     */
    public static int dateDiffDay(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return -1;
        }
        double f = (date2.getTime()-date1.getTime())/(3600*24*1000);
        return (int) Math.floor(f) + 1;
    }

    /**
     * 根据定义的样式,将date转化为Str
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (date == null) return "";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 指定当前日期往前推的某个星期
     * @param date 指定的时间
     * @param i 星期几,星期日为1
     */
    public static Date dateLastWeek(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int iday = 7 + cal.get(Calendar.DAY_OF_WEEK) - i;
        return dateAdd(date, Calendar.DAY_OF_MONTH, -iday);
    }

    /**
     * 将date转为星期
     * @param date
     * @return
     */
    public static String dateToWeak(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)-1) {
            case 0:
                return "星期日";
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            default:
                return "星期日";
        }
    }

    /**
     * 获取日期中某个值，年、月、日、小时
     * @param workDay
     * @param calendar: {@link Calendar}
     * @return
     */
    public static int getDateField(Date workDay, int calendar) {
        Calendar cal =  Calendar.getInstance();
        cal.setTime(workDay);
        return cal.get(calendar);
    }

    /**
     * @Title: toTimeZone
     * @Description:		转换时区
     * @param date
     * @param format
     * @param timeZone1		所属时区
     * @param timeZone2		目标时区
     * @return
     * @throws ParseException
     * String    返回类型
     * @throws
     */
    public static String toTimeZone(String date, String format, String timeZone1, String timeZone2) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone1));
        Date day;
        try {
            day = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone2));
        return sdf.format(day);
    }

    public static String toTimeZone(String date, String format1, String format2, String timeZone1, String timeZone2) {
        SimpleDateFormat sdf = new SimpleDateFormat(format1);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone1));
        Date day;
        try {
            day = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        sdf = new SimpleDateFormat(format2);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone2));
        return sdf.format(day);
    }
}
