package main_service_application.controller.admin_controller;

import main_service_application.dto.category.CategoryDto;
import main_service_application.dto.category.CategoryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PatchMapping
    public CategoryDto patchCategory(@RequestBody CategoryDto dto) {
        return categoryService.patchCategory(dto);
    }

    @PostMapping
    public CategoryDto addNewCategory(@RequestBody CategoryPostDto dto) {
        return categoryService.addNewCategory(dto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable Long catId) {
        categoryService.deleteCategory(catId);
    }
}
