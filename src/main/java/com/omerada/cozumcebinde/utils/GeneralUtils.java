package com.omerada.cozumcebinde.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.util.StringUtils;
import com.omerada.cozumcebinde.config.JacksonMaxDepth;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.omerada.cozumcebinde.config.JacksonMaxDepth.MAX_OBJECT_DEPTH_ON_SERIALIZATION;


public class GeneralUtils {

    private GeneralUtils() {
    }

    private static final long MEGABYTE = 1024L * 1024L;

    private static final Logger logger = LogManager.getLogger(GeneralUtils.class);

    private static final String GENERAL_UTILS = "GeneralUtils : ";

    /**
     * Bütün element içeriklerinin null olması durumunda true donecektir
     *
     * @param array
     * @return
     */
    public static boolean areAllElementsNull(Object[] array) {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean valueNullOrEmpty(final T value) {
        return value == null ||
                (value instanceof String && ((String) ((String) value).trim()).isEmpty()) ||
                (value instanceof Collection && ((Collection<?>) value).isEmpty()) ||
                (value instanceof byte[] && ((byte[]) value).length == 0);
    }

    public static String convertToJSONOld(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(obj);
    }

    public static String convertToJSON(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setFilterProvider(
                new SimpleFilterProvider().
                        addFilter(JacksonMaxDepth.DepthAwarePropertyFilter.FILTER_ID,
                                new JacksonMaxDepth.DepthAwarePropertyFilter(MAX_OBJECT_DEPTH_ON_SERIALIZATION)));
        objectMapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new JacksonMaxDepth.DepthAwareBeanSerializerModifier());
            }
        });
        return objectMapper.writeValueAsString(obj);
    }

    public static String moneyFormatter(BigDecimal money) {
        // Apply the custom symbols to the formatter
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
        String format = formatter.format(money);
        return format;
    }

    public static String moneyFormatterBefore2005(BigDecimal money) {
        // Apply the custom symbols to the formatter
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(money);
    }

    public static BigDecimal parseNumber(String number) {
        if (number == null || number.isEmpty()) {
            return BigDecimal.ZERO;
        }
        String sanitizedNumber = number.replace(".", "").replace(",", ".");
        return new BigDecimal(sanitizedNumber);
    }

    public static <T> T convertToObject(String json, Class<T> clazz) {
        T obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            obj = mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(GENERAL_UTILS + e.getLocalizedMessage());
        }
        return obj;
    }


    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

    public static Object clone(final Object object) {
        if (object == null) {
            return null;
        }
        try {
            return GeneralUtils.convertToObject(GeneralUtils.convertToJSON(object), object.getClass());
        } catch (JsonProcessingException e) {
            logger.error(GENERAL_UTILS + e.getLocalizedMessage());
        }
        return null;
    }

    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = null;
        if (request != null) {
            remoteAddr = request.getHeader(Constants.X_FORWARDED_FOR);
            if (valueNullOrEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public static Boolean getIsAllowedIP(HttpServletRequest request, String allowedIpList) {
        Boolean isAllowedIP = false;
        if (valueNullOrEmpty(allowedIpList)) {
            return isAllowedIP;
        }
        List<String> allowedIPList = Arrays.asList(allowedIpList.split(","));
        for (String allowedIP : allowedIPList) {
            if (GeneralUtils.getClientIp(request) != null && GeneralUtils.getClientIp(request).contains(allowedIP)) {
                isAllowedIP = true;
                break;
            }
        }
        return isAllowedIP;
    }

    public static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= Constants.PASSWORD_MIN_LENGTH &&
                password.length() <= Constants.PASSWORD_MAX_LENGTH;
    }


    public static boolean isValidTckn(Long tckn) {
        try {
            String tmp = tckn.toString();
            if (tmp.length() == 11) {
                int totalOdd = 0;
                int totalEven = 0;
                for (int i = 0; i < 9; i++) {
                    int val = Integer.parseInt(tmp.substring(i, i + 1));
                    if (i % 2 == 0)
                        totalOdd += val;
                    else
                        totalEven += val;
                }
                int total = totalOdd + totalEven + Integer.valueOf(tmp.substring(9, 10));
                int lastDigit = total % 10;
                if (tmp.substring(10).equals(String.valueOf(lastDigit))) {
                    int parityCheck = ((totalOdd * 7) - totalEven) % 10;
                    if (tmp.substring(9, 10).equals(String.valueOf(parityCheck)))
                        return true;
                }
            }
        } catch (Exception e) {
            logger.error(GENERAL_UTILS + e.getLocalizedMessage());
        }
        return false;
    }

    public static String formatMoneyByDate(BigDecimal amount, LocalDate date) {
        if (amount.compareTo(BigDecimal.ZERO) == 0 || amount == null) {
            return date.isBefore(LocalDate.of(2005, 1, 1)) ? "0":"0,00";
        } else {
            BigDecimal res = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
            if (date.isBefore(LocalDate.of(2005, 1, 1))) {
                return moneyFormatterBefore2005(res);  // 2015 öncesi format
            } else {
                return moneyFormatter(res);  // 2015 sonrası format
            }
        }
    }

    /**
     * PARAMETRE VERİLEN DEĞERİN SIFIRDAN FARKLI OLUP,
     * NULL KONTROLÜNÜ YAPAN YARDIMCI METOT.
     *
     * @param value
     * @return
     */
    public static boolean notNullOrZero(Long value) {
        return value != null && value != 0;
    }

    /**
     * PARAMETRE VERİLEN DEĞERİN SIFIRDAN FARKLI OLUP,
     * NULL KONTROLÜNÜ YAPAN YARDIMCI METOT.
     *
     * @param value
     * @return
     */
    public static boolean notNullOrZero(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * PARAMETRE VERİLEN DEĞERİN POZİTİF OLUP,
     * NULL KONTROLÜNÜ YAPAN YARDIMCI METOT.
     *
     * @param value
     * @return
     */
    public static boolean notNullOrPositive(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * PARAMETRE OLARAK VERİLEN AY VE YIL DEĞERİNİ
     * FORMATLAYIP INT TÜRÜNDE FORMATLI OLARAK DÖNEN YARDIMCI METOT.
     *
     * @param yil,ay
     * @return
     */
    public static int parseDonem(int yil, int ay) {
        return Integer.parseInt(yil + (ay < 10 ? "0" + ay : ay + ""));
    }

    /**
     * PARAMETRE OLARAK VERİLEN AY VE YIL DEĞERİNİ
     * FORMATLAYIP LONG TÜRÜNDE FORMATLI OLARAK DÖNEN YARDIMCI METOT.
     *
     * @param yil,ay
     * @return
     */
    public static long parseDonem(long yil, long ay) {
        return Long.parseLong(yil + (ay < 10 ? "0" + ay : ay + ""));
    }

    /**
     * Kontrol: String Bir alanın null veya boş olup olmadığını kontrol eder.
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
