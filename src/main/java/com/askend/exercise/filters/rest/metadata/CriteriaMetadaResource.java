package com.askend.exercise.filters.rest.metadata;


import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CriteriaMetadaResource {

    CriteriaType criteriaType;
    List<String> comparingConditions;

}
