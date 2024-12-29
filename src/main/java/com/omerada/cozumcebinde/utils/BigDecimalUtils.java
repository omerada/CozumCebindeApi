package com.omerada.cozumcebinde.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BigDecimalUtils {

    public static BigDecimal min(BigDecimal amount1, BigDecimal amount2) {
        if (amount1.compareTo(amount2) <= 0) {
            return amount1;
        }
        return amount2;
    }

    public static BigDecimal max(BigDecimal amount1, BigDecimal amount2) {
        if (amount1.compareTo(amount2) >= 0) {
            return amount1;
        }
        return amount2;
    }
}
