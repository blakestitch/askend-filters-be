package com.askend.exercise.filters.domain.validator;

import com.askend.exercise.filters.domain.AllowedComparingConditionsService;
import com.askend.exercise.filters.domain.model.Criteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CriteriaAllowedComparingConditionValidatorUnitTest {

    @Mock
    private AllowedComparingConditionsService allowedComparingConditionsService;

    @InjectMocks
    private CriteriaAllowedComparingConditionValidator validator;

    @Test
    void shouldBeValid() {
        // given
        Criteria criteria = Criteria.builder().value("myValue").build();
        given(allowedComparingConditionsService.getConditionsByCriteriaType(any())).willReturn(List.of("abc", "myValue"));

        // when // then
        validator.validate(criteria);
    }

    @Test
    void shouldBeInvalid() {
        // given
        Criteria criteria = Criteria.builder().value("myValue").build();
        given(allowedComparingConditionsService.getConditionsByCriteriaType(any())).willReturn(List.of("abc", "NotMyValue"));

        // when // then
        assertThatExceptionOfType(CriteriaValidationException.class)
                .isThrownBy(() -> validator.validate(criteria)).withMessage("Not allowed comparing condition for given type");

    }
}