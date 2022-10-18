package statistics_service_application.mapper;

import statistics_service_application.dto.HitDto;
import statistics_service_application.model.Hit;

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
