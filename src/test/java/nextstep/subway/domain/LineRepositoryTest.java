package nextstep.subway.domain;

import nextstep.subway.ui.StationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    StationRepository stationRepository;

    @DisplayName("저장 테스트")
    @Test
    void saveLine() {
        // when
        Line saved = lineRepository.save(LineTest.line1);
        // then
        assertThat(saved.getId()).isEqualTo(LineTest.line1.getId());
    }

    @Transactional
    @DisplayName("조회 테스트")
    @Test
    void selectLine() {
        // when
        Line saved = lineRepository.save(LineTest.line1);
        Line selected = lineRepository.findById(LineTest.line1.getId()).get();
        // then
        System.out.println(selected);
        assertThat(selected.getId()).isEqualTo(LineTest.line1.getId());
    }

}