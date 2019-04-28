package com.whuaz.java.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author grez
 * @since 19-4-28
 **/
public class DateUtilTest {


    public static void main(String[] args) {

//        toDate();
        durationAndPeriod();
    }

    private void localDate() {
        LocalDate date = LocalDate.of(2018, 2, 13);
        System.out.println("LocalDate: " + date);
        date = LocalDate.parse("2018-02-13");
        System.out.println("LocalDate: " +date);
    }

    private void localTime() {
        LocalTime time = LocalTime.of(6, 30);
        System.out.println("LocalTime: " + time);

        time = LocalTime.parse("06:30");
        System.out.println("LocalTime: " + time);
    }

    private void localDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2018, 2, 13, 6, 30);
        System.out.println("LocalDateTime: " + dateTime);
        dateTime = LocalDateTime.parse("2018-02-13T06:30");
        System.out.println("LocalDateTime: " + dateTime);
    }

    /**
     * LocalDate/LocalTime转LocalDateTime
     */
    private void toLocalDateTime() {
        LocalDateTime dateTime;
        // LocalDate to LocalDateTime
        dateTime = LocalDate.parse("2018-02-13").atTime(LocalTime.parse("06:30"));
        System.out.println("LocalDate to LocalDateTime: " + dateTime);
        // LocalTime to LocalDateTime
        dateTime = LocalTime.parse("06:30").atDate(LocalDate.parse("2018-02-13"));
        System.out.println("LocalTime to LocalDateTime: " + dateTime);
    }

    /**
     * LocalDateTime转LocalDate/LocalTime
     */
    private void convertLocalDateOrTime() {
        LocalDate date;
        LocalTime time;
        // LocalDateTime to LocalDate/LocalTime
        date = LocalDateTime.parse("2018-02-13T06:30").toLocalDate();
        time = LocalDateTime.parse("2018-02-13T06:30").toLocalTime();
    }

    /**
     * 日期 时间相加减操作
     */
    private void dateTimeCaculate() {
        LocalDate date;
        date = LocalDate.parse("2018-02-13").plusDays(5);
        System.out.println(date);
        date = LocalDate.parse("2018-02-13").plus(3, ChronoUnit.MONTHS);
        System.out.println(date);

        LocalTime time;
        time = LocalTime.parse("06:30").minusMinutes(30);
        System.out.println(time);
        time = LocalTime.parse("06:30").minus(500, ChronoUnit.MILLIS);
        System.out.println(time);

        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse("2018-02-13T06:30").plus(Duration.ofHours(2));
        System.out.println(dateTime);

        date = LocalDate.parse("2018-02-13").with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date);
    }

    /**
     * Date转LocalDateTime
     */
    private void dateToLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println(dateTime);
    }

    /**
     * LocalDateTime转Date
     */
    private static void toDate() {
        Date now = new Date();

        LocalDateTime dateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());

        Date date = Date.from(dateTime.toInstant(ZoneOffset.ofHours(1)));
        System.out.println(date);
        date = Date.from(dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime)));
        System.out.println(date);

    }

    /**
     * 日期时间差
     */
    private static void durationAndPeriod() {
        Period period = Period.between(LocalDate.parse("2018-01-18"), LocalDate.parse("2018-02-14"));
        System.out.println(period.getDays());

        // 获取时间差，以秒或纳秒表示
        Duration duration = Duration.between(LocalDateTime.parse("2018-01-18T06:30"), LocalDateTime.parse("2018-02" +
                "-14T22:58"));
        System.out.println(duration.getSeconds());

        // represents a period of 27 days
        period = Period.parse("P27D");
    }

    /**
     * 格式化
     */
    private static void format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][dd/MM/yyyy][MM-dd-yyyy]");
        LocalDate date = LocalDate.parse("09-23-2018", formatter);
        System.out.println(date);
        date = LocalDate.parse("23/09/2018", formatter);
        System.out.println(date);
        date = LocalDate.parse("2018-09-23", formatter);
        System.out.println(date);

        formatter = new DateTimeFormatterBuilder()
                .appendOptional( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) )
                .optionalStart().appendPattern( "dd/MM/yyyy" ).optionalEnd()
                .optionalStart().appendPattern( "MM-dd-yyyy" ).optionalEnd()
                .toFormatter();


        formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy-MMM-dd]" );
        LocalDate.parse( "2018-09-23", formatter );
        LocalDate.parse( "2018-Sep-23", formatter );
        // Using the ofPattern example where we reuse the common part of the pattern
        formatter = DateTimeFormatter.ofPattern( "yyyy-[MM-dd][MMM-dd]" );
        LocalDate.parse( "2018-09-23", formatter );
        LocalDate.parse( "2018-Sep-23", formatter );
    }
}
