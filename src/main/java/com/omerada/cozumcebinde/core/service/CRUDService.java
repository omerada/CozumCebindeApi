package com.omerada.cozumcebinde.core.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.omerada.cozumcebinde.core.domain.BaseModel;

import java.util.List;
import java.util.Optional;

public abstract class CRUDService<T extends BaseModel,R extends JpaRepository> implements ICRUDService<T> {

    private R repository;

    public CRUDService(R repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return (T) repository.save(t);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
