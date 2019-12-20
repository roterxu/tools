package top.xujie.tools.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author : xujie
 */
public class LocalDateUtil {

    /**
     * 日期格式yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期格式yyyy年dd月
     */
    public static String DATE_PATTERN2 = "yyyy年MM月";

    /**
     * 日期格式yyyy年dd月
     */
    public static String DATE_PATTERN3 = "yyyy-MM";

    /**
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String TIME_PATTERN = "HH:mm:ss";

    /**
     * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
     */
    public static DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 构造函数
     */
    private LocalDateUtil() {
        super();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date Date对象
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME_PATTERN);
    }

    /**
     * 格式化时间为字符串-默认yyyy-MM-dd 格式
     *
     * @param date LocalDate对象
     * @return
     */
    public static String formatLocalDateToStr(LocalDate date, String pattern) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * String 转时间-默认yyyy-MM-dd格式
     *
     * @param timeStr 字符串
     * @return
     */
    public static LocalDate parseStrToLocalDate(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }
        return LocalDate.parse(timeStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * LocalDateTime
     *
     * @param localDateTime
     * @return yyyy-MM-dd
     */
    public static String parseLocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_DATE_FORMATTER);
    }

    /**
     * 按pattern格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime LocalDateTime对象
     * @param pattern  要格式化的字符串
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return "";
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * String 转时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param timeStr 字符串
     * @return
     */
    public static LocalDateTime parseStrToLocalDateTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 获取今天的00:00:00
     *
     * @return
     */
    public static String getDayStart() {
        return getDayStart(LocalDateTime.now());
    }

    /**
     * 获取今天的23:59:59
     *
     * @return
     */
    public static String getDayEnd() {
        return getDayEnd(LocalDateTime.now());
    }

    /**
     * 获取某天的00:00:00
     *
     * @param dateTime
     * @return
     */
    public static String getDayStart(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MIN));
    }

    /**
     * 获取某天的23:59:59
     *
     * @param dateTime
     * @return
     */
    public static String getDayEnd(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MAX));
    }

    /**
     * 获取本月第一天的00:00:00
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        return getFirstDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取本月最后一天的23:59:59
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        return getLastDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取某月第一天的00:00:00
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static String getFirstDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
    }

    /**
     * 获取某月最后一天的23:59:59
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static String getLastDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        /*System.out.println(getDayStart());
        System.out.println(getDayEnd());
        System.out.println(getFirstDayOfMonth());
        System.out.println(getLastDayOfMonth());
        System.out.println(LocalDateUtil.formatDateTime(LocalDateTime.now(), LocalDateUtil.TIME_PATTERN));
        System.out.println(parseStrToLocalDateTime("2018-12-12 10:10:10"));*/
        System.out.println(formatLocalDateToStr(LocalDate.now(), DATE_PATTERN3));
        /*System.out.println(parseStrToLocalDate("2019-12-12"));
        System.out.println(LocalDate.now().toString().substring(0, 7));*/
    }
}
