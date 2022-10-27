package ru.practicum.ewm_ms.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompilationPostDto {
    private List<Long> events;
    private Boolean pinned;
    private String title;
}
