package com.askend.exercise.filters.rest.filter.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CriteriaResource {

    @NotNull
    CriteriaType type;

    @NotBlank
    @Size(min = 1, max = 50)
    String comparingCondition;

    @NotBlank
    @Size(min = 1, max = 50)
    String value;

}
