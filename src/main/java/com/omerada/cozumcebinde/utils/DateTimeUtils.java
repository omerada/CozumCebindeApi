package com.omerada.cozumcebinde.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    private static String TIME_ZONE_TR = "Europe/Istanbul";

    public static String format(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static String localDateToString(LocalDate date) {
        return String.format(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : "" + date.getDayOfMonth())  +
                (date.getMonthValue() < 10 ? "0" + date.getMonthValue() :  date.getMonthValue()) +  +
                date.getYear();
    }

    public static boolean isDateLessThanOrEqual(LocalDate date1, LocalDate date2) {
        return !date1.isAfter(date2);
    }
    public static boolean isDateBeforeThanOrEqual(LocalDate date1, LocalDate date2) {
        return !date1.isBefore(date2);
    }

    public static String getNowDate() {
        Date nowDate = new Date();
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dmyFormat.format(nowDate);
    }

    public static String getNowTime() {
        Format f = new SimpleDateFormat("HH:mm:ss");
        String strResult = f.format(new Date());
        return strResult;
    }

    public static Date getNowDateTime() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return java.sql.Date.valueOf(localDate.format(dateFormatter));
    }

    public static String getTimeNow() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localTime.format(timeFormatter);
    }

    public static Date getDate(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return java.sql.Date.valueOf(localDate.format(dateFormatter));
    }

    public static LocalDate stringToLocalDate(String formattedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(formattedDate, formatter);

    }

    public static LocalDate getDb2CompatibleDate(String silmeTarihi) {
        return LocalDate.now();
    }

    public static LocalDate dateToLocaleDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }



}


