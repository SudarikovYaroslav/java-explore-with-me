package mappers;

import dto.category.CategoryDto;
import dto.category.CategoryPostDto;
import model.Category;

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