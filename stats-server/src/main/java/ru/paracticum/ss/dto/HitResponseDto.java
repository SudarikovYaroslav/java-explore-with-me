package ru.paracticum.ss.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitResponseDto {
    private String app;
    private String uri;
    private Long hits;
}
