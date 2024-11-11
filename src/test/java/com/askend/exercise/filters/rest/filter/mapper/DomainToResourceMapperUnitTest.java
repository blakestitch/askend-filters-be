package com.askend.exercise.filters.rest.filter.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.CriteriaType;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.rest.filter.resource.CriteriaResource;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DomainToResourceMapperUnitTest {

    private final DomainToResourceMapper mapper = new DomainToResourceMapper();

    @Test
    void shouldMapToResource() {
        // given
        Filter filter = Filter.builder().id(1L).name("name").criterias(List.of()).build();

        // when
        FilterResource filterResource = mapper.mapToResource(filter);

        // then
        assertThat(filterResource.getId()).isEqualTo(1L);
        assertThat(filterResource.getName()).isEqualTo("name");
        assertThat(filterResource.getCriterias()).isEmpty();
    }

    @Test
    void shouldMapCriteriaToResource() {
        // given
        Filter filter = Filter.builder().criterias(List.of(Criteria.builder()
                .comparingCondition("compare")
                .type(CriteriaType.AMOUNT)
                .value("22")
                .build())).build();

        // when
        CriteriaResource criteriaResource = mapper.mapToResource(filter).getCriterias().stream().findFirst().get();

        // then
        assertThat(criteriaResource.getType()).isEqualTo(com.askend.exercise.filters.rest.filter.resource.CriteriaType.AMOUNT);
        assertThat(criteriaResource.getComparingCondition()).isEqualTo("compare");
        assertThat(criteriaResource.getValue()).isEqualTo("22");
    }
}