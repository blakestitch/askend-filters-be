package com.askend.exercise.filters.domain.validator;

import com.askend.exercise.filters.domain.model.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CriteriaValidator {

    private final CriteriaConversionValidator criteriaConversionValidator;
    private final CriteriaAllowedComparingConditionValidator criteriaAllowedComparingConditionValidator;

    public void validate(List<Criteria> criterias) {
        criterias.forEach(criteria ->  {
            criteriaConversionValidator.validate(criteria);
            criteriaAllowedComparingConditionValidator.validate(criteria);
        });
    }
}
