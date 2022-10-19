package ru.paracticum.ewm_ss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.paracticum.ewm_ss.dto.HitDto;
import ru.paracticum.ewm_ss.dto.StatisticsDto;
import ru.paracticum.ewm_ss.mapper.HitMapper;
import ru.paracticum.ewm_ss.model.Hit;
import ru.paracticum.ewm_ss.repository.HitRepository;

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
        return null;
    }
}
