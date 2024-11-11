package com.askend.exercise.filters.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Criteria {

    CriteriaType type;

    String comparingCondition;

    String value;

}
