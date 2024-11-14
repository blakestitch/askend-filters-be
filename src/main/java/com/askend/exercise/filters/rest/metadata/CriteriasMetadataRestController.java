package com.askend.exercise.filters.rest.metadata;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/criterias-metadata")
@RequiredArgsConstructor
public class CriteriasMetadataRestController {

    private final CriteriasMetadataResourceAggregator aggregator;

    @GetMapping
    public List<CriteriaMetadaResource> getAllMetadata() {
        return aggregator.getAllMetadata();
    }
}
