package dto.compilation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompilationPostDto {
    private Long[] events;
    private Boolean pinned;
    private String title;
}
