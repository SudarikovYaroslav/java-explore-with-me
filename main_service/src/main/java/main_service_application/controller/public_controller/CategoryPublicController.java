package main_service_application.controller.public_controller;

import main_service_application.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryPublicController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                     @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return categoryService.findAll(from, size);
    }

    @GetMapping("/catId")
    public CategoryDto findById(@PathVariable Long catId) {
        return categoryService.findById(catId);
    }
}
