package com.askend.exercise.filters.rest.comparingconditions;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/comparing-conditions")
@RequiredArgsConstructor
public class ComparingConditionsRestController {

    private final ComparingConditionsResourceAggregator aggregator;

    @GetMapping
    public List<ComparingConditionsResource> getAllConditions() {
        return aggregator.getAllConditions();
    }
}
