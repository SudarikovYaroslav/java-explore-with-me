package ru.practicum.ewm_ms.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm_ms.util.CommonValidMarker;
import ru.practicum.ewm_ms.util.PatchValidMarker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {
    @NotNull(groups = PatchValidMarker.class)
    private Long id;
    @NotBlank(groups = CommonValidMarker.class)
    private String name;
}
