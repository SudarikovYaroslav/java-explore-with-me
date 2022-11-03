package ru.practicum.ms.dto.category;

import lombok.*;
import ru.practicum.ms.util.CommonValidMarker;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPostDto {
    @NotBlank(groups = CommonValidMarker.class)
    private String name;

    @Override
    public String toString() {
        return "CategoryPostDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
