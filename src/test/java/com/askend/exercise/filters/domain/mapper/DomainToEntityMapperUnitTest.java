package com.askend.exercise.filters.domain.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.CriteriaType;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.persistence.entity.CriteriaEntity;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DomainToEntityMapperUnitTest {

    private final DomainToEntityMapper domainToEntityMapper = new DomainToEntityMapper();

    @Test
    void shouldMapToEntityToFilterEntity() {
        // given
        Filter filter = Filter.builder().id(1L).name("name").criterias(null).build();

        // when
        FilterEntity filterEntity = domainToEntityMapper.mapToEntity(filter);

        // then
        assertThat(filterEntity.getId()).isNull();
        assertThat(filterEntity.getName()).isEqualTo("name");
        assertThat(filterEntity.getCriterias()).isNull();
    }

    @Test
    void shouldMapToEntityToFilterCriteria() {
        // given
        Filter filter = Filter.builder().criterias(List.of(Criteria.builder()
                .comparingCondition("compare")
                .type(CriteriaType.AMOUNT)
                .value("22")
                .build())).build();

        // when
        CriteriaEntity criteriaEntity = domainToEntityMapper.mapToEntity(filter).getCriterias().stream().findFirst().get();

        // then
        assertThat(criteriaEntity.getType()).isEqualTo(com.askend.exercise.filters.persistence.entity.CriteriaType.AMOUNT);
        assertThat(criteriaEntity.getComparingCondition()).isEqualTo("compare");
        assertThat(criteriaEntity.getValue()).isEqualTo("22");
    }

}