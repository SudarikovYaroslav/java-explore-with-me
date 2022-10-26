package ru.paracticum.ewm_ss.mapper;

import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.model.Hit;
import ru.paracticum.ewm_ss.repository.HitRepository;

public class HitMapper {

    private HitMapper() {}

    public static Hit toModel(HitPostDto dto) {
        return Hit.builder()
                .id(dto.getId())
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .timeStamp(DateTimeMapper.toDateTime(dto.getTimeStamp()))
                .build();
    }

    public static  HitResponseDto toDto(Hit hit, HitRepository repo) {
        return HitResponseDto.builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .hits(repo.getCountHits(hit.getUri()))
                .build();
    }
}