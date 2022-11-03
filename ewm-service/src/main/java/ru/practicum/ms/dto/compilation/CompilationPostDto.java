package ru.practicum.ms.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompilationPostDto {
    private List<Long> events;
    private Boolean pinned;
    @NotBlank
    private String title;

    @Override
    public String toString() {
        return "CompilationPostDto{" +
                "events=" + events +
                ", pinned=" + pinned +
                ", title='" + title + '\'' +
                '}';
    }
}
