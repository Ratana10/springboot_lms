package com.kongsun.leanring.system.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity
                .ok()
                .body(categories.stream()
                        .map(categoryMapper::toCategoryDTO)
                        .toList()
                );

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        category = categoryService.create(category);
        return ResponseEntity
                .status(CREATED)
                .body(categoryMapper.toCategoryDTO(category));

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity
                .status(FOUND)
                .body(categoryMapper.toCategoryDTO(category));

    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        category = categoryService.update(id, category);
        return ResponseEntity
                .ok()
                .body(categoryMapper.toCategoryDTO(category));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity
                .status(FOUND)
                .body("delete successfully");

    }

}
