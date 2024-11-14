package com.askend.exercise.filters.rest.metadata;

import com.askend.exercise.filters.domain.AllowedComparingConditionsService;
import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CriteriasMetadataResourceAggregator {

    private final AllowedComparingConditionsService allowedComparingConditionsService;

    public List<CriteriaMetadaResource> getAllMetadata() {
        return List.of(CriteriaMetadaResource.builder()
                        .criteriaType(CriteriaType.AMOUNT)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.AMOUNT)).build(),
                CriteriaMetadaResource.builder()
                        .criteriaType(CriteriaType.DATE)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.DATE)).build(),
                CriteriaMetadaResource.builder()
                        .criteriaType(CriteriaType.TITLE)
                        .comparingConditions(allowedComparingConditionsService.getConditionsByCriteriaType(com.askend.exercise.filters.domain.model.CriteriaType.TITLE)).build());

    }

}
