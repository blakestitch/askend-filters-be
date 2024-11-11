package com.askend.exercise.filters.domain.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.persistence.entity.CriteriaEntity;
import com.askend.exercise.filters.persistence.entity.CriteriaType;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainToEntityMapper {

    public FilterEntity mapToEntity(Filter filter) {
        return FilterEntity.builder()
                .name(filter.getName())
                .criterias(filter.getCriterias().stream().map(this::mapCriteria).toList())
                .build();
    }

    private CriteriaEntity mapCriteria(Criteria criteria) {
        return CriteriaEntity.builder()
                .type(CriteriaType.valueOf(criteria.getType().name()))
                .comparingCondition(criteria.getComparingCondition())
                .value(criteria.getValue())
                .build();
    }
}
