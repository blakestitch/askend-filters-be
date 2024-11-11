package com.askend.exercise.filters.rest.filter.mapper;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.rest.filter.resource.CriteriaResource;
import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceToDomainMapperUnitTest {

    private final ResourceToDomainMapper mapper = new ResourceToDomainMapper();

    @Test
    void shouldMapToDomain() {
        // given
        FilterResource filterResource = FilterResource.builder().id(1L).name("name").criterias(List.of()).build();

        // when
        Filter filter = mapper.mapToDomain(filterResource);

        // then
        assertThat(filter.getId()).isNull();
        assertThat(filter.getName()).isEqualTo("name");
        assertThat(filter.getCriterias()).isEmpty();
    }

    @Test
    void shouldMapMapToDomainWithCriteria() {
        // given
        FilterResource filterResource = FilterResource.builder().criterias(List.of(CriteriaResource.builder()
                .comparingCondition("compare")
                .type(CriteriaType.AMOUNT)
                .value("22")
                .build())).build();

        // when
        Criteria criteria = mapper.mapToDomain(filterResource).getCriterias().stream().findFirst().get();

        // then
        assertThat(criteria.getType()).isEqualTo(com.askend.exercise.filters.domain.model.CriteriaType.AMOUNT);
        assertThat(criteria.getComparingCondition()).isEqualTo("compare");
        assertThat(criteria.getValue()).isEqualTo("22");
    }

}