package com.omerada.cozumcebinde.dto.data;

import lombok.Data;

@Data
public class GridResultModel {

	private Object data;
	private Long totalCount;

	public GridResultModel() {
		this.totalCount = 0L;
	}

	public GridResultModel(Object data, Long totalCount) {
		this.data = data;
		this.totalCount = totalCount;
	}

}
