package com.omerada.cozumcebinde.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Constants {

    public static final Long SUBE_KOD= 1L;
    public static final Long IL_KOD = 6L;

//    public static final Long USER_ID = 13705656L;

    public static final Long ADMIN_USER_ID = 1L;
    public static final Long SYSTEM_USER_ID = 2L;
    public static final Long ROOT_ORGANIZATION_ID = 1L;
    public static final String ACTIVE = "A";
    public static final String PASSIVE = "P";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final int MAX_LOGIN_RETRY = 5;
    public static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";

    public static final int PASSWORD_MIN_LENGTH = 64;
    public static final int PASSWORD_MAX_LENGTH = 64;
    public static final int[] AYIN_GUNLERI = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final String SCHEMA = "SIVTBD01";
    public static final Long[] KANUN_NO = {6111L,6736L,7020L,7143L,7256L,7326L,7440L};
    public static final Long C_DEVIR_YILI = 2000L;
    public static final Long[] TAHAKKUK_KANUN_NO = {6111L,6552L,6736L,7020L,7143L,7256L,7326L,7440L};
    public static final LocalDate DEVIR_SON_TARIH = LocalDate.of(2001, 1, 2);
    public static final BigDecimal MILLION = BigDecimal.valueOf(1000000L);
    public static final BigDecimal FIVE_TL = BigDecimal.valueOf(5L);
    public static final BigDecimal HUNDRED_DECIMAL = BigDecimal.valueOf(100L);
}
