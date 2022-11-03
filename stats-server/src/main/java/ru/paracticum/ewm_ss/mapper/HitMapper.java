package ru.paracticum.ewm_ss.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.model.App;
import ru.paracticum.ewm_ss.model.Hit;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HitMapper {

    public static Hit toModel(HitPostDto dto, App app) {
        Hit hit = Hit.builder()
                .hitId(dto.getId())
                .app(app)
                .uri(dto.getUri())
                .ip(dto.getIp())
                .eventId(dto.getEventId())
                .build();
        if (dto.getTimeStamp() != null) {
            hit.setTimeStamp(DateTimeMapper.toDateTime(dto.getTimeStamp()));
        } else {
            hit.setTimeStamp(LocalDateTime.now());
        }
        return hit;
    }

    public static HitResponseDto toDto(Hit hit, Long countHits) {
        return HitResponseDto.builder()
                .app(hit.getApp().getName())
                .uri(hit.getUri())
                .hits(countHits)
                .build();
    }
}