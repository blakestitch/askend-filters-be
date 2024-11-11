package com.askend.exercise.filters.persistence.repository;

import com.askend.exercise.filters.persistence.entity.CriteriaEntity;
import com.askend.exercise.filters.persistence.entity.CriteriaType;
import com.askend.exercise.filters.persistence.entity.FilterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FilterRepositoryDataJpaTest {

    @Autowired
    private FilterRepository filterRepository;

    @Test
    void shouldSaveSuccessfullyWithCorrectOrmMappings() {
        // given
        FilterEntity givenEntity = FilterEntity.builder()
                .name("test").criterias(List.of(CriteriaEntity.builder()
                        .value("value")
                        .type(CriteriaType.TITLE)
                        .comparingCondition("comparr")
                        .build(), CriteriaEntity.builder()
                        .value("value2")
                        .type(CriteriaType.TITLE)
                        .comparingCondition("comparr2")
                        .build()))
                .build();

        // when
        FilterEntity entity = filterRepository.save(givenEntity);

        // then
        FilterEntity result = filterRepository.findById(entity.getId()).get();
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getName()).isEqualTo("test");
        assertThat(result.getCriterias()).hasSize(2);
        assertThat(result.getCriterias().getFirst().getType()).isEqualTo(CriteriaType.TITLE);
        assertThat(result.getCriterias().getFirst().getValue()).isEqualTo("value");
        assertThat(result.getCriterias().getFirst().getComparingCondition()).isEqualTo("comparr");

    }
}