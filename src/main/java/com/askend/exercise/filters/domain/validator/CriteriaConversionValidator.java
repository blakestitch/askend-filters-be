package com.askend.exercise.filters.domain.validator;

import com.askend.exercise.filters.domain.model.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class CriteriaConversionValidator {

    public void validate(Criteria criteria) {
        switch (criteria.getType()) {
            case DATE -> {
                isDateParseable(criteria.getValue());
            }
            case AMOUNT -> {
                isAmountParseable(criteria.getValue());
            }
        }
    }

    private void isDateParseable(String value) {
        try {
            LocalDate.parse(value);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new CriteriaValidationException("Date not parsable");
        }

    }

    private void isAmountParseable(String value) {
        try {
            Long.valueOf(value);
        } catch (NumberFormatException numberFormatException) {
            throw new CriteriaValidationException("Amount not parsable");
        }
    }

}
