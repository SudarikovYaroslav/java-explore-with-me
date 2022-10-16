package controller.admin_controller;

import dto.category.CategoryDto;
import dto.category.CategoryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesAdminController {

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
