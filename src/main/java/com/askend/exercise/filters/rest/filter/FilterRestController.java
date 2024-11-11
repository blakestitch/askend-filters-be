package com.askend.exercise.filters.rest.filter;

import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.domain.service.FilterService;
import com.askend.exercise.filters.rest.filter.mapper.DomainToResourceMapper;
import com.askend.exercise.filters.rest.filter.mapper.ResourceToDomainMapper;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/filters")
@RequiredArgsConstructor
public class FilterRestController {

    private final FilterService filterService;
    private final ResourceToDomainMapper resourceToDomainMapper;
    private final DomainToResourceMapper domainToResourceMapper;

    @GetMapping
    public List<FilterResource> getAllFilters() {
        return filterService.getAll().stream().map(domainToResourceMapper::mapToResource).toList();
    }

    @PostMapping
    public void createFilter(@Validated @RequestBody FilterResource filterResource) {
        Filter filter = resourceToDomainMapper.mapToDomain(filterResource);
        filterService.create(filter);
    }

}
