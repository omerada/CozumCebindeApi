package com.omerada.cozumcebinde.dto.data;

import lombok.Data;

@Data
public class GridFilter {

	private String name;
	
	private Object value;
	
	private String type;
	
	private String conjunction;
	
	private String subType;
	
	private String filterMatchMode;

}
