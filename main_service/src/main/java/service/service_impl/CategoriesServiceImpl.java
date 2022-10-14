package service.service_impl;

import dto.category.CategoryDto;
import dto.category.CategoryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.CategoriesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

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
