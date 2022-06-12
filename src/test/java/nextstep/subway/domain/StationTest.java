package nextstep.subway.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StationTest {
    public static final Station station1 = new Station("강남역", LineTest.line1);
    public static final Station station2 = new Station("신논현역", LineTest.line1);

    @Autowired
    StationRepository stationRepository;

    @Transactional
    @DisplayName("Line save test")
    @Test
    void saveLine() {
        Station station = stationRepository.save(station1);
        stationRepository.flush();
        assertThat(stationRepository.findAll()).isNotEmpty();


    }
}