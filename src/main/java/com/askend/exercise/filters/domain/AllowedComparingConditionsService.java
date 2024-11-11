package com.askend.exercise.filters.domain;

import com.askend.exercise.filters.domain.model.CriteriaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AllowedComparingConditionsService {

    private static final List<String> CONDITIONS_FOR_AMOUNT = List.of("amount condition one", "amount condition two");
    private static final List<String> CONDITIONS_FOR_TITLE = List.of("title condition one", "title condition two", "title condition three");
    private static final List<String> CONDITIONS_FOR_DATE = List.of("date condition one", "date condition two");

    private static final Map<CriteriaType, List<String>> CONDITIONS_BY_CRITERIA_TYPE = createConditions();

    private static Map<CriteriaType, List<String>> createConditions() {
        return Map.of(CriteriaType.AMOUNT, CONDITIONS_FOR_AMOUNT, CriteriaType.TITLE, CONDITIONS_FOR_TITLE, CriteriaType.DATE, CONDITIONS_FOR_DATE);
    }

    public List<String> getConditionsByCriteriaType(CriteriaType criteriaType) {
        return CONDITIONS_BY_CRITERIA_TYPE.get(criteriaType);
    }

}
