package statistics_service_application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import statistics_service_application.dto.HitDto;
import statistics_service_application.dto.StatisticsDto;
import statistics_service_application.mapper.DateTimeMapper;
import statistics_service_application.mapper.HitMapper;
import statistics_service_application.model.Hit;
import statistics_service_application.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final HitRepository hitRepository;

    @Override
    public void postHit(HitDto dto) {
        Hit hit = HitMapper.toModel(dto);
        hitRepository.save(hit);
    }

    @Override
    public List<StatisticsDto> getStatistics(String start, String end, String[] uris, Boolean unique) {
        LocalDateTime decodedStart = DateTimeMapper.toDateTime(decoder(start));
        LocalDateTime decodedEnd = DateTimeMapper.toDateTime(decoder(end));
        return null;
    }

    private String decoder(String encoded) {
        return new String(Base64.getDecoder().decode(encoded.getBytes()));
    }
}
