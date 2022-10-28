package ru.practicum.ewm_ms.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm_ms.util.CommonValidMarker;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(groups = CommonValidMarker.class)
    private String name;
}
