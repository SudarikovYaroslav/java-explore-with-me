package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.category.CategoryDto;
import ru.practicum.ewm_ms.dto.category.CategoryPostDto;
import ru.practicum.ewm_ms.repository.CategoryRepository;
import ru.practicum.ewm_ms.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    //TODO реализовать логику методов, добавить маппер и репозиторий
    @Override
    public List<CategoryDto> findAll(Integer from, Integer size) {
        return null;
    }

    //TODO need realisation
    @Override
    public CategoryDto findById(Long catId) {
        return null;
    }

    //TODO need realisation
    @Override
    public CategoryDto patchCategory(CategoryDto dto) {
        return null;
    }

    //TODO need realisation
    @Override
    public CategoryDto addNewCategory(CategoryPostDto dto) {
        return null;
    }

    //TODO need realisation
    @Override
    public void deleteCategory(Long catId) {
    }
}