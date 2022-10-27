package ru.practicum.ewm_ms.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
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
