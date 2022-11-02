package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_ms.dto.category.CategoryDto;
import ru.practicum.ewm_ms.dto.category.CategoryPostDto;
import ru.practicum.ewm_ms.exception.ForbiddenException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.mappers.CategoryMapper;
import ru.practicum.ewm_ms.model.Category;
import ru.practicum.ewm_ms.repository.CategoryRepository;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.service.CategoryService;
import ru.practicum.ewm_ms.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm_ms.util.Util.isCategoryEmpty;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Override
    public List<CategoryDto> findAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Category> categories = categoryRepository.findAll(pageable).toList();
        return categories.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(Util.getCategoryNotFoundMessage(catId)));
        return CategoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryDto patchCategory(CategoryDto dto) {
        Category pathingCat = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(Util.getCategoryNotFoundMessage(dto.getId())));
        pathingCat.setName(dto.getName());
        return CategoryMapper.toDto(pathingCat);
    }

    @Override
    @Transactional
    public CategoryDto addNewCategory(CategoryPostDto dto) {
        Category newCat = CategoryMapper.toModel(dto);
        newCat = categoryRepository.save(newCat);
        return CategoryMapper.toDto(newCat);
    }

    @Override
    @Transactional
    public void deleteCategory(Long catId) {
        if (!isCategoryEmpty(catId, eventRepository)) {
            throw new ForbiddenException("Category id: " + catId + " is not empty");
        }
        categoryRepository.deleteById(catId);
    }
}