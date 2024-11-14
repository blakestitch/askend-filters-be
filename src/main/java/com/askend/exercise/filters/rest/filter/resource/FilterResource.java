package com.askend.exercise.filters.rest.filter.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FilterResource {

    @NotBlank
    @Size(min = 1, max = 50)
    String name;

    @Valid
    @NotEmpty
    @Size(max = 20)
    List<CriteriaResource> criterias;
}
