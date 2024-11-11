package com.askend.exercise.filters.domain;

import com.askend.exercise.filters.domain.model.CriteriaType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AllowedComparingConditionsServiceUnitTest {

    private final AllowedComparingConditionsService allowedComparingConditionsService = new AllowedComparingConditionsService();

    @Test
    void shouldFindByDateCriteriaType() {
        // given
        CriteriaType criteriaType = CriteriaType.DATE;

        // when
        List<String> result = allowedComparingConditionsService.getConditionsByCriteriaType(criteriaType);

        // then
        assertThat(result).containsExactly("date condition one", "date condition two");
    }

    @Test
    void shouldFindByAmountCriteriaType() {
        // given
        CriteriaType criteriaType = CriteriaType.AMOUNT;

        // when
        List<String> result = allowedComparingConditionsService.getConditionsByCriteriaType(criteriaType);

        // then
        assertThat(result).containsExactly("amount condition one", "amount condition two");
    }

    @Test
    void shouldFindByTitleCriteriaType() {
        // given
        CriteriaType criteriaType = CriteriaType.TITLE;

        // when
        List<String> result = allowedComparingConditionsService.getConditionsByCriteriaType(criteriaType);

        // then
        assertThat(result).containsExactly("title condition one", "title condition two", "title condition three");
    }
}
