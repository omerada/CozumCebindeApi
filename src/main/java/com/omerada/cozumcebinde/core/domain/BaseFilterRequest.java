package com.omerada.cozumcebinde.core.domain;

import lombok.Data;

@Data
public class BaseFilterRequest<T> {
    T filter;
    SortingDTO sorting;
}
