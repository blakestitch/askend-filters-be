package com.askend.exercise.filters.domain.validator;

import com.askend.exercise.filters.domain.model.Criteria;
import com.askend.exercise.filters.domain.model.CriteriaType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class CriteriaConversionValidatorUnitTest {

    private final CriteriaConversionValidator validator = new CriteriaConversionValidator();

    @Test
    void shouldSucceedWithTitleCriteriaType() {
        // given
        Criteria criteria = Criteria.builder()
                .type(CriteriaType.TITLE)
                .value("vall")
                .build();

        // when
        validator.validate(criteria);
    }

    @Test
    void shouldSucceedWithCorrectDateCriteriaType() {
        // given
        Criteria criteria = Criteria.builder()
                .type(CriteriaType.DATE)
                .value("2010-01-01")
                .build();

        // when
        validator.validate(criteria);
    }

    @Test
    void shouldFailWithIncorrectDateCriteriaType() {
        // given
        Criteria criteria = Criteria.builder()
                .type(CriteriaType.DATE)
                .value("2010ddd-01-01")
                .build();

        // when // then
        assertThatExceptionOfType(CriteriaValidationException.class)
                .isThrownBy(() -> validator.validate(criteria)).withMessage("Date not parsable");
    }

    @Test
    void shouldSucceedWithCorrectAmountCriteriaType() {
        // given
        Criteria criteria = Criteria.builder()
                .type(CriteriaType.DATE)
                .value("100")
                .build();

        // when
        validator.validate(criteria);
    }

    @Test
    void shouldFailWithIncorrectAmountCriteriaType() {
        // given
        Criteria criteria = Criteria.builder()
                .type(CriteriaType.DATE)
                .value("sss")
                .build();

        // when // then
        assertThatExceptionOfType(CriteriaValidationException.class)
                .isThrownBy(() -> validator.validate(criteria)).withMessage("Amount not parsable");
    }
}