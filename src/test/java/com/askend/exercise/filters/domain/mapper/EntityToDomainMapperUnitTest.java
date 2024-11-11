package com.askend.exercise.filters.domain.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.persistence.entity.CriteriaEntity;
import com.askend.exercise.filters.persistence.entity.CriteriaType;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntityToDomainMapperUnitTest {

    private final EntityToDomainMapper entityToDomainMapper = new EntityToDomainMapper();

    @Test
    void shouldMapMapToDomain() {
        // given
        FilterEntity filterEntity = FilterEntity.builder().id(1L).name("name").criterias(null).build();

        // when
        Filter filter = entityToDomainMapper.mapToDomain(filterEntity);

        // then
        assertThat(filter.getId()).isEqualTo(1L);
        assertThat(filter.getName()).isEqualTo("name");
        assertThat(filter.getCriterias()).isNull();
    }

    @Test
    void shouldMapMapToDomainWithCriteria() {
        // given
        FilterEntity filter = FilterEntity.builder().criterias(List.of(CriteriaEntity.builder()
                .comparingCondition("compare")
                .type(CriteriaType.AMOUNT)
                .value("22")
                .build())).build();

        // when
        Criteria criteria = entityToDomainMapper.mapToDomain(filter).getCriterias().stream().findFirst().get();

        // then
        assertThat(criteria.getType()).isEqualTo(com.askend.exercise.filters.domain.model.CriteriaType.AMOUNT);
        assertThat(criteria.getComparingCondition()).isEqualTo("compare");
        assertThat(criteria.getValue()).isEqualTo("22");
    }
}