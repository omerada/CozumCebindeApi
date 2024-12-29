package com.omerada.cozumcebinde.utils;

/**
 * Verilen ilk parametre ile ikinci parametreyi Comparable<T>
 * jenerik sınıfını kullanarak karşılaştırır.
 * Özelleklikle floating point içeren numeriklerin (Double, BigDecimal)
 * karşılaştırılması için bu yaklaşım gereklidir.
 * örnek kullanım: Comparison.compareIf(firstBigDecimal).isGreaterThan(secondDecimal) -> true/false
 *
 * @author Esat Koç
 */
public class Comparison<T> {

    private final Comparable<T> first;

    private Comparison(Comparable<T> first) {
        this.first = first;
    }

    public static <T> Comparison<T> compareThat(Comparable<T> first) {
        return new Comparison<>(first);
    }

    public boolean isLessThan(T other) {
        if (first == null || other == null) {
            throw new IllegalArgumentException("comparable arguments cannot be null");
        }
        return first.compareTo(other) < 0;
    }

    public boolean isLessThanOrEqualTo(T other) {
        if (first == null || other == null) {
            throw new IllegalArgumentException("comparable arguments cannot be null");
        }
        return first.compareTo(other) <= 0;
    }

    public boolean isEqualTo(T other) {
        if (first == null || other == null) {
            throw new IllegalArgumentException("comparable arguments cannot be null");
        }
        return first.compareTo(other) == 0;
    }

    public boolean isGreaterThan(T other) {
        if (first == null || other == null) {
            throw new IllegalArgumentException("comparable arguments cannot be null");
        }
        return first.compareTo(other) > 0;
    }

    public boolean isGreaterThanOrEqualTo(T other) {
        if (first == null || other == null) {
            throw new IllegalArgumentException("comparable arguments cannot be null");
        }
        return first.compareTo(other) >= 0;
    }
}
