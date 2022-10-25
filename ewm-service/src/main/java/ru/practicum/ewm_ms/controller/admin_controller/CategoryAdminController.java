package ru.practicum.ewm_ms.controller.admin_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.category.CategoryDto;
import ru.practicum.ewm_ms.dto.category.CategoryPostDto;
import ru.practicum.ewm_ms.service.CategoryService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PatchMapping
    public CategoryDto patchCategory(@RequestBody CategoryDto dto) {
        log.info("patch category {}", dto);
        return categoryService.patchCategory(dto);
    }

    @PostMapping
    public CategoryDto addNewCategory(@RequestBody CategoryPostDto dto) {
        log.info("create new category: {}", dto);
        return categoryService.addNewCategory(dto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable Long catId) {
        log.info("delete category id: {}", catId);
        categoryService.deleteCategory(catId);
    }
}
