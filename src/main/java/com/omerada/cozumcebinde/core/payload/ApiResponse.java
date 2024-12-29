package com.omerada.cozumcebinde.core.payload;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T object;

    public ResponseEntity<?> getResponseJson(ApiResponse apiResponse){
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ApiResponse(){}

    public ApiResponse(Boolean success, String message,T o) {
        this.success = success;
        this.message = message;
        this.object = o;
    }
}
