package ru.practicum.ms.service;

import ru.practicum.ms.dto.category.CategoryDto;
import ru.practicum.ms.dto.category.CategoryPostDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Integer from, Integer size);

    CategoryDto findById(Long catId);

    CategoryDto patchCategory(CategoryDto dto);

    CategoryDto addNewCategory(CategoryPostDto dto);

    void deleteCategory(Long catId);

}
