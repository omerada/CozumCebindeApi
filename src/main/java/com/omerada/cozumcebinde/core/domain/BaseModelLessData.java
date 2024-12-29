package com.omerada.cozumcebinde.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseModelLessData<T extends BaseDTOLessData> {
    @Transient
    @JsonIgnore
    private ModelMapper modelMapper = new ModelMapper();

    public abstract T toDTO();
}
