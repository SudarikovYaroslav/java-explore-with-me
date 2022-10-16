package main_service_application.mappers;

import main_service_application.dto.category.CategoryDto;
import main_service_application.dto.category.CategoryPostDto;
import main_service_application.model.Category;

public class CategoryMapper {

    private CategoryMapper() {}

    public static Category toModel(CategoryPostDto dto) {
        return Category.builder()
                .id(null)
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