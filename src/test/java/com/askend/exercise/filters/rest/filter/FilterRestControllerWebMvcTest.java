package com.askend.exercise.filters.rest.filter;

import com.askend.exercise.filters.domain.model.Filter;
import com.askend.exercise.filters.domain.service.FilterService;
import com.askend.exercise.filters.domain.validator.CriteriaValidationException;
import com.askend.exercise.filters.rest.filter.mapper.DomainToResourceMapper;
import com.askend.exercise.filters.rest.filter.mapper.ResourceToDomainMapper;
import com.askend.exercise.filters.rest.filter.resource.CriteriaResource;
import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import com.askend.exercise.filters.rest.filter.resource.FilterResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FilterRestController.class)
class FilterRestControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilterService filterService;

    @MockBean
    private ResourceToDomainMapper resourceToDomainMapper;

    @MockBean
    private DomainToResourceMapper domainToResourceMapper;


    @Test
    void shouldGetFilters() throws Exception {
        // given
        given(filterService.getAll()).willReturn(List.of(Filter.builder().build()));
        given(domainToResourceMapper.mapToResource(any())).willReturn(FilterResource.builder()
                .name("a name")
                .criterias(List.of(CriteriaResource.builder()
                        .comparingCondition("comparing1")
                        .type(CriteriaType.DATE)
                        .value("2020-01-01")
                        .build()))
                .build());

        // when // then
        this.mockMvc.perform(get("/api/filters")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("a name"))
                .andExpect(jsonPath("$[0].criterias[0].comparingCondition").value("comparing1"))
                .andExpect(jsonPath("$[0].criterias[0].type").value("DATE"))
                .andExpect(jsonPath("$[0].criterias[0].value").value("2020-01-01"));
    }


    @Test
    void shouldCreateFilter() throws Exception {
        // given
        String validFilterRequest = getValidFilterRequest();

        // when // then
        this.mockMvc.perform(post("/api/filters").contentType(MediaType.APPLICATION_JSON).content(validFilterRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturnBadRequestWhenRequestValidationFails() throws Exception {
        // given
        String invalidFilterRequest = invalidFilterRequest();

        // when // then
        this.mockMvc.perform(post("/api/filters").contentType(MediaType.APPLICATION_JSON).content(invalidFilterRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenCriteriaValidationFails() throws Exception {
        // given
        String validFilterRequest = getValidFilterRequest();
        doThrow(new CriteriaValidationException("errrror")).when(filterService).create(any());

        // when // then
        this.mockMvc.perform(post("/api/filters").contentType(MediaType.APPLICATION_JSON).content(validFilterRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String getValidFilterRequest() {
        try {
            return objectMapper.writeValueAsString(FilterResource.builder()
                    .name("a name")
                    .criterias(List.of(CriteriaResource.builder()
                            .value("val")
                            .type(CriteriaType.TITLE)
                            .comparingCondition("condition")
                            .build()))
                    .build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String invalidFilterRequest() {
        try {
            return objectMapper.writeValueAsString(FilterResource.builder()
                    .name("   ")
                    .criterias(List.of(CriteriaResource.builder()
                            .value("  ")
                            .comparingCondition("condition")
                            .build()))
                    .build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}