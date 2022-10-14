package controller.admin_controller;

import dto.category.CategoryDto;
import dto.category.CategoryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.CategoriesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesAdminController {

    private final CategoriesService categoriesService;

    @PatchMapping
    public CategoryDto patchCategory(@RequestBody CategoryDto dto) {
        return categoriesService.patchCategory(dto);
    }

    @PostMapping
    public CategoryDto addNewCategory(@RequestBody CategoryPostDto dto) {
        return categoriesService.addNewCategory(dto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable Long catId) {
        categoriesService.deleteCategory(catId);
    }
}
