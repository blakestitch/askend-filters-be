package com.askend.exercise.filters.rest.filter.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.CriteriaType;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.rest.filter.resource.CriteriaResource;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import org.springframework.stereotype.Component;

@Component
public class ResourceToDomainMapper {

    public Filter mapToDomain(FilterResource filterResource) {
        return Filter.builder()
                .name(filterResource.getName())
                .criterias(filterResource.getCriterias().stream().map(this::mapCriteria).toList())
                .build();
    }

    private Criteria mapCriteria(CriteriaResource criteriaResource) {
        return Criteria.builder()
                .type(CriteriaType.valueOf(criteriaResource.getType().name()))
                .comparingCondition(criteriaResource.getComparingCondition())
                .value(criteriaResource.getValue())
                .build();
    }
}
