package com.omerada.cozumcebinde.core.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SortingDTO {
    private String name;
    private Sort.Direction direction;
}
