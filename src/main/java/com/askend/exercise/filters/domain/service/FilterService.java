package com.askend.exercise.filters.domain.service;

import com.askend.exercise.filters.persistence.repository.FilterRepository;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.domain.mapper.DomainToEntityMapper;
import com.askend.exercise.filters.domain.mapper.EntityToDomainMapper;
import com.askend.exercise.filters.domain.validator.CriteriaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;
    private final CriteriaValidator criteriaValidator;

    public void create(Filter filter) {
        criteriaValidator.validate(filter.getCriterias());
        FilterEntity filterEntity = domainToEntityMapper.mapToEntity(filter);
        filterRepository.save(filterEntity);
    }

    public List<Filter> getAll() {
        return filterRepository.findAll().stream().map(entityToDomainMapper::mapToDomain).toList();
    }
}
