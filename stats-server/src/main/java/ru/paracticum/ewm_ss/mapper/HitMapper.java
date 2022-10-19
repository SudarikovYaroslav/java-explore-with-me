package ru.paracticum.ewm_ss.mapper;

import ru.paracticum.ewm_ss.dto.HitDto;
import ru.paracticum.ewm_ss.model.Hit;

public class HitMapper {

    private HitMapper() {}

    public static Hit toModel(HitDto dto) {
        return Hit.builder()
                .id(dto.getId())
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .timeStamp(DateTimeMapper.toDateTime(dto.getTimeStamp()))
                .build();
    }
}