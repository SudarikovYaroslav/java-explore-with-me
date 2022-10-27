package ru.practicum.ewm_ms.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitDto {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private String timeStamp;

    public HitDto(String app, String uri, String ip, String timeStamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timeStamp = timeStamp;
    }
}