package main_service_application.service.service_impl;

import main_service_application.dto.category.CategoryDto;
import main_service_application.dto.category.CategoryPostDto;
import lombok.RequiredArgsConstructor;
import main_service_application.service.CategoryService;
import org.springframework.stereotype.Service;
import main_service_application.repository.CategoryRepository;

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