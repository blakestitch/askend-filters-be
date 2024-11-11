package com.askend.exercise.filters.domain.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.CriteriaType;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.persistence.entity.CriteriaEntity;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDomainMapper {

    public Filter mapToDomain(FilterEntity entity) {
        return Filter.builder()
                .id(entity.getId())
                .name(entity.getName())
                .criterias(entity.getCriterias().stream().map(this::mapCriteria).toList())
                .build();
    }

    private Criteria mapCriteria(CriteriaEntity criteriaEntity) {
        return Criteria.builder()
                .type(CriteriaType.valueOf(criteriaEntity.getType().name()))
                .comparingCondition(criteriaEntity.getComparingCondition())
                .value(criteriaEntity.getValue())
                .build();
    }
}
