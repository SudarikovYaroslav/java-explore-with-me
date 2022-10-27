package ru.practicum.ewm_ms.dto.category;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPostDto {
    @NotBlank
    private String name;

    @Override
    public String toString() {
        return "CategoryPostDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
