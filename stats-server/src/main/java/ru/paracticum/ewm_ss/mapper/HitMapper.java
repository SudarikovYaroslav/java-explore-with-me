package ru.paracticum.ewm_ss.mapper;

import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.model.Hit;
import ru.paracticum.ewm_ss.repository.HitRepository;

import java.time.LocalDateTime;

public class HitMapper {

    private HitMapper() {}

    public static Hit toModel(HitPostDto dto) {
        Hit hit = Hit.builder()
                .hit_id(dto.getId())
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .build();
        if (dto.getTimeStamp() != null) {
            hit.setTimeStamp(DateTimeMapper.toDateTime(dto.getTimeStamp()));
        } else {
            hit.setTimeStamp(LocalDateTime.now());
        }
        return hit;
    }

    public static  HitResponseDto toDto(Hit hit, HitRepository repo) {
        return HitResponseDto.builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .hits(repo.getCountHits(hit.getUri()))
                .build();
    }
}