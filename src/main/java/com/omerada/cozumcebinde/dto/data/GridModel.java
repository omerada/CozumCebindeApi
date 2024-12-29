package com.omerada.cozumcebinde.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class GridModel {
	private List<GridFilter> filter;
	private List<GridSort> sort;
	private Integer pageNumber;
	private Integer limit;

}
