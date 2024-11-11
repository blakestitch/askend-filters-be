package com.askend.exercise.filters.rest.comparingconditions;

import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import com.askend.exercise.filters.domain.AllowedComparingConditionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ComparingConditionsResourceAggregator {

    private final AllowedComparingConditionsService allowedComparingConditionsService;

    public List<ComparingConditionsResource> getAllConditions() {
        return List.of(ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.AMOUNT)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.AMOUNT)).build(),
                ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.DATE)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.DATE)).build(),
                ComparingConditionsResource.builder()
                        .criteriaType(CriteriaType.TITLE)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.TITLE)).build());

    }

}
