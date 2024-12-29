package com.omerada.cozumcebinde.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTOLessData<T extends BaseModelLessData> {
    @JsonIgnore
    private ModelMapper modelMapper = new ModelMapper();

    public abstract T toEntity();

}
