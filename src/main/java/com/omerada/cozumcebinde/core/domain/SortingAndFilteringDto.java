package com.omerada.cozumcebinde.core.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Data
public class SortingAndFilteringDto {
    private String name;
    private Sort.Direction direction;

    private Map<String,String> filter;
}
