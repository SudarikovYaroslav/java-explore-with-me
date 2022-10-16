package main_service_application.service;

import main_service_application.dto.category.CategoryDto;
import main_service_application.dto.category.CategoryPostDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Integer from, Integer size);

    CategoryDto findById(Long catId);

    CategoryDto patchCategory(CategoryDto dto);

    CategoryDto addNewCategory(CategoryPostDto dto);

    void deleteCategory(Long catId);

}
