package com.omerada.cozumcebinde.core.service;

import java.util.List;
import java.util.Optional;

public interface  ICRUDService<T> {

    T save(T t);

    List<T> findAll();

    Optional<T> findById(Long id);

    void deleteById(Long id);

}
