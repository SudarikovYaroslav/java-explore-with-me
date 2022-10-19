package ru.practicum.ewm_ms.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompilationPostDto {
    private Long[] events;
    private Boolean pinned;
    private String title;
}
