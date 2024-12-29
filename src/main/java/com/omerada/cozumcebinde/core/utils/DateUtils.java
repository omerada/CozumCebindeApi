package com.omerada.cozumcebinde.core.utils;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    private static String TIME_ZONE_TR = "Europe/Istanbul";

    private final static String PATTERN_WITH_TIME_2 = "yyyy-MM-dd HH:mm XXX";

    private final static String PATTERN_WITH_TIME_3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static String SPLIT_CHAR = ":";
    private static int ZERO =  0;

    @Getter
    private final static String PATTERN = "dd.MM.yyy";
    private final static String PATTERN2 = "yyyy-MM-dd";
    private static SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);

    public static LocalDateTime now (){
        return LocalDateTime.now(ZoneId.of(TIME_ZONE_TR));
    }

    public static LocalDateTime dateToLocalDatetime(Date date,String time){
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
                        ZoneId.of(TIME_ZONE_TR));

        String[] timeArr = time.split(SPLIT_CHAR);
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);

        return LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth(),hour,minute,ZERO,ZERO);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(TIME_ZONE_TR))
                .toLocalDate();
    }

    public static String dateTostr (Date date){
        return formatter.format(date);
    }

    public static String getTimeZoneTr() {
        return TIME_ZONE_TR;
    }

    public static Date stringToDate(String value) {
        try {
            return formatter.parse(value);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    public static String getPatternWithTime2() {
        return PATTERN_WITH_TIME_2;
    }

    public static String getPatternWithTime3() {
        return PATTERN_WITH_TIME_3;
    }

    public static String getPattern2() {
        return PATTERN2;
    }
}
