package com.omerada.cozumcebinde.core.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaginationResponse {
    private Object data;
    private long currentPageNumber;
    private long totalPageCount;
    private long totalCount;
}
