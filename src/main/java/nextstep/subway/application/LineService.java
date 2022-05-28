package nextstep.subway.application;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.StationRepository;
import nextstep.subway.dto.LineRequest;
import nextstep.subway.dto.LineResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LineService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public LineService(LineRepository lineRepository, StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public LineResponse saveLine(LineRequest request) {
        final Line line = lineRepository.save(
            request.toLine()
                    .withUpStation(stationRepository.getById(request.getUpStationId()))
                    .withDownStation(stationRepository.getById(request.getDownStationId()))
        );

        return LineResponse.of(line);
    }

    @Transactional(readOnly = true)
    public List<LineResponse> findAllLine() {
        return lineRepository.findAll()
                .stream()
                .map(LineResponse::of)
                .collect(Collectors.toList());
    }

    public void updateLine(Long id, LineRequest lineRequest) {
        Line findLine = lineRepository.findById(id).get();
        findLine.changeColor(lineRequest.getColor());
        findLine.changeName(lineRequest.getName());
        lineRepository.flush();
    }

    @Transactional(readOnly = true)
    public LineResponse findLine(Long lindId) {
        return LineResponse.of(lineRepository.findById(lindId).get());
    }

    public void deleteLine(Long lindId) {
        lineRepository.deleteById(lindId);
    }


    private Station getValidStation(LineRequest request, String s) {
        return stationRepository.findById(request.getUpStationId())
                .orElseThrow(() -> new IllegalArgumentException(s));
    }
}
