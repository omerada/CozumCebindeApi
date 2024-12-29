package com.omerada.cozumcebinde.core.payload;


import lombok.Data;

@Data
public class ServiceErrorResponse {

    private String message;

    private String httpCode;

    private boolean success;

    private String detailMessage;
}
