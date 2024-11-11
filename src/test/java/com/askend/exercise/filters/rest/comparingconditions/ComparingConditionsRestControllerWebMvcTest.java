package com.askend.exercise.filters.rest.comparingconditions;

import com.askend.exercise.filters.rest.filter.resource.CriteriaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComparingConditionsRestController.class)
class ComparingConditionsRestControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComparingConditionsResourceAggregator comparingConditionsResourceAggregator;

    @Test
    void shouldReturnSomeConditions() throws Exception {
        // given
        given(comparingConditionsResourceAggregator.getAllConditions()).willReturn(List.of(ComparingConditionsResource.builder()
                .criteriaType(CriteriaType.TITLE)
                .comparingConditions(List.of("one", "two"))
                .build()));

        // when // then
        this.mockMvc.perform(get("/api/comparing-conditions")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].criteriaType").value("TITLE"))
                .andExpect(jsonPath("$[0].comparingConditions[0]").value("one"))
                .andExpect(jsonPath("$[0].comparingConditions[1]").value("two"));
    }
}