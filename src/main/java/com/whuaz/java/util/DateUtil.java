package com.whuaz.java.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @author grez
 * @since 19-4-28
 **/
public class DateUtil {


    /**
     * date类型转指定日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期字符串转date类型
     * 字符串格式精确到时间 如：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static Date toDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return Date.from(dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime)));
    }

    /**
     * the days to add, may be negative
     */
    public static Date plusDays(Date date, long daysToAdd) {
        if (daysToAdd == 0) {
            return date;
        }
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusDays(daysToAdd);
        return Date.from(dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime)));
    }

    /**
     * 指定天、星期、月、年相加
     * @param date 日期
     * @param amountToAdd add数
     * @param unit ChronoUnit类
     *                  MONTHS
     *                  DAYS
     *                  WEEKS
     *                  YEARS
     * @return 日期
     */
    public static Date plus(Date date, long amountToAdd, TemporalUnit unit) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        dateTime = dateTime.plus(amountToAdd, unit);
        return Date.from(dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime)));
    }

    /**
     * 计算日期相差天数
     */
    public static long until(Date from, Date to) {
        LocalDateTime fromDateTime = LocalDateTime.ofInstant(from.toInstant(), ZoneId.systemDefault());
        LocalDateTime toDateTime = LocalDateTime.ofInstant(to.toInstant(), ZoneId.systemDefault());
        return fromDateTime.until(toDateTime, ChronoUnit.DAYS);
    }
}
