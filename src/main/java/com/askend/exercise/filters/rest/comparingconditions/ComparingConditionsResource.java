package com.askend.exercise.filters.rest.comparingconditions;


import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ComparingConditionsResource {

    CriteriaType criteriaType;
    List<String> comparingConditions;

}
