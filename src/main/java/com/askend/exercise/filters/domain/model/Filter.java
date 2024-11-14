package com.askend.exercise.filters.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class Filter {

    String name;

    List<Criteria> criterias;

}
