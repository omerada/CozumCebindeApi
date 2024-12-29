package com.omerada.cozumcebinde.core.service;


import com.omerada.cozumcebinde.core.payload.ApiResponse;

public interface IService<T> {

    ApiResponse save(T t);

    ApiResponse findAll();

    ApiResponse findById(Long id);

    void deleteById(Long id);

}
