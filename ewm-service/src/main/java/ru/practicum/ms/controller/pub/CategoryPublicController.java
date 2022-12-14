package ru.practicum.ms.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ms.dto.category.CategoryDto;
import ru.practicum.ms.service.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryPublicController {

    public static final String DEFAULT_FROM = "0";
    public static final String DEFAULT_SIZE = "10";

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll(@PositiveOrZero
                                     @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                     @Positive
                                     @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        log.info("find all categories from:{}, size:{}", from, size);
        return categoryService.findAll(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto findById(@Positive
                                @PathVariable Long catId) {
        log.info("find category by id:{}", catId);
        return categoryService.findById(catId);
    }
}
