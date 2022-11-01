package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.dto.category.CategoryDto;
import ru.practicum.ewm_ms.dto.category.CategoryPostDto;
import ru.practicum.ewm_ms.model.Category;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {

    public static Category toModel(CategoryPostDto dto) {
        return Category.builder()
                .id(null)
                .name(dto.getName())
                .build();
    }

    public static Category toModel(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}