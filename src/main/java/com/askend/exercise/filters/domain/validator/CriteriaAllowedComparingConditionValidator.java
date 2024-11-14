package com.askend.exercise.filters.domain.validator;


import com.askend.exercise.filters.domain.AllowedComparingConditionsService;
import com.askend.exercise.filters.domain.model.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriteriaAllowedComparingConditionValidator {

    private final AllowedComparingConditionsService allowedComparingConditionsService;

    public void validate(Criteria criteria) {
        boolean isAllowed = allowedComparingConditionsService.getConditionsByCriteriaType(criteria.getType()).contains(criteria.getComparingCondition());
        if (!isAllowed) {
            throw new CriteriaValidationException("Not allowed comparing condition for given type");
        }
    }
}
