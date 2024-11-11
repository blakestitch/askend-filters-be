package com.askend.exercise.filters.rest.comparingconditions;

import com.askend.exercise.filters.domain.AllowedComparingConditionsService;
import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ComparingConditionsResourceAggregatorUnitTest {

    @Mock
    private AllowedComparingConditionsService allowedComparingConditionsService;

    @InjectMocks
    private ComparingConditionsResourceAggregator aggregator;

    @BeforeEach
    void setUp() {
        given(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.AMOUNT)).willReturn(List.of("cond1"));
        given(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.DATE)).willReturn(List.of("cond2"));
        given(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.TITLE)).willReturn(List.of("cond3"));
    }

    @Test
    void shouldGetAllConditionsForAmount() {
        // given // when
        List<ComparingConditionsResource> allConditions = aggregator.getAllConditions();

        // then
        assertThat(allConditions)
                .filteredOn(comparingConditionsResource -> comparingConditionsResource.getCriteriaType() == CriteriaType.AMOUNT)
                .containsExactly(ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.AMOUNT)
                        .comparingConditions(List.of("cond1"))
                        .build());

    }

    @Test
    void shouldGetAllConditionsForDate() {
        // given // when
        List<ComparingConditionsResource> allConditions = aggregator.getAllConditions();

        // then
        assertThat(allConditions)
                .filteredOn(comparingConditionsResource -> comparingConditionsResource.getCriteriaType() == CriteriaType.DATE)
                .containsExactly(ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.DATE)
                        .comparingConditions(List.of("cond2"))
                        .build());

    }

    @Test
    void shouldGetAllConditionsForTitle() {
        // given // when
        List<ComparingConditionsResource> allConditions = aggregator.getAllConditions();

        // then
        assertThat(allConditions)
                .filteredOn(comparingConditionsResource -> comparingConditionsResource.getCriteriaType() == CriteriaType.TITLE)
                .containsExactly(ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.TITLE)
                        .comparingConditions(List.of("cond3"))
                        .build());

    }
}