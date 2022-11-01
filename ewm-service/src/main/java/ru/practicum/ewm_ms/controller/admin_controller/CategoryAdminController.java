package ru.practicum.ewm_ms.controller.admin_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_ms.dto.category.CategoryDto;
import ru.practicum.ewm_ms.dto.category.CategoryPostDto;
import ru.practicum.ewm_ms.service.CategoryService;
import ru.practicum.ewm_ms.util.CommonValidMarker;

import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PatchMapping
    public CategoryDto patchCategory(@Validated({CommonValidMarker.class})
                                     @RequestBody CategoryDto dto) {
        log.info("patch category {}", dto);
        return categoryService.patchCategory(dto);
    }

    @PostMapping
    public CategoryDto addNewCategory(@Validated({CommonValidMarker.class})
                                      @RequestBody CategoryPostDto dto) {
        log.info("create new category: {}", dto);
        return categoryService.addNewCategory(dto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@Positive
                               @PathVariable Long catId) {
        log.info("delete category id: {}", catId);
        categoryService.deleteCategory(catId);
    }
}
