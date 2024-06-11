package com.kongsun.leanring.system.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO dto) {
        Category category = CategoryMapper.INSTANCE.toCategory(dto);
        category = categoryService.create(category);
        return ResponseEntity
                .status(CREATED)
                .body(CategoryMapper.INSTANCE.toCategoryDTO(category));

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity
                .status(CREATED)
                .body(CategoryMapper.INSTANCE.toCategoryDTO(category));

    }

}
