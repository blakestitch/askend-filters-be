package com.askend.exercise.filters.rest.filter.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.rest.filter.resource.CriteriaResource;
import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import org.springframework.stereotype.Component;

@Component
public class DomainToResourceMapper {

    public FilterResource mapToResource(final Filter filter) {
        return FilterResource.builder()
                .id(filter.getId())
                .name(filter.getName())
                .criterias(filter.getCriterias().stream().map(this::mapCriteria).toList())
                .build();
    }

    private CriteriaResource mapCriteria(Criteria criteria) {
        return CriteriaResource.builder()
                .value(criteria.getValue())
                .comparingCondition(criteria.getComparingCondition())
                .type(CriteriaType.valueOf(criteria.getType().name()))
                .build();
    }

}
