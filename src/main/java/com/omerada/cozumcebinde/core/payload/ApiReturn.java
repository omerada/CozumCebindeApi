package com.omerada.cozumcebinde.core.payload;

public class ApiReturn {

    public static ApiResponse falseResponse(String message) {
        System.out.println("Api Warning ---> " + message);
        return new ApiResponse(false, message, null);
    }

    public static ApiResponse trueResponse(Object object) {
        return new ApiResponse(true, null, object);
    }



}
