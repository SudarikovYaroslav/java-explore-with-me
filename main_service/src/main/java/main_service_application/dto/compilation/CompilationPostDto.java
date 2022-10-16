package main_service_application.dto.compilation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompilationPostDto {
    private Long[] events;
    private Boolean pinned;
    private String title;
}
