package controller.public_controller;

import dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.CategoriesService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoriesPublicController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final CategoriesService categoriesService;

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                     @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return categoriesService.findAll(from, size);
    }

    @GetMapping("/catId")
    public CategoryDto findById(@PathVariable Long catId) {
        return categoriesService.findById(catId);
    }
}
