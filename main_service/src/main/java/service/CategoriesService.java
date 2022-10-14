package service;

import dto.category.CategoryDto;
import dto.category.CategoryPostDto;

import java.util.List;

public interface CategoriesService {
    List<CategoryDto> findAll(Integer from, Integer size);

    CategoryDto findById(Long catId);

    CategoryDto patchCategory(CategoryDto dto);

    CategoryDto addNewCategory(CategoryPostDto dto);

    void deleteCategory(Long catId);

}
