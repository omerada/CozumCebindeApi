package com.omerada.cozumcebinde.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO<T extends BaseModel> {
    @JsonIgnore
    private ModelMapper modelMapper = new ModelMapper();

    private Long id;

    private int deleted;

    public abstract T toEntity();

}
