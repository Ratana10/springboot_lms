package com.kongsun.leanring.system.category;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Category> categories = categoryService.getAll();

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(categories.stream()
                                .map(categoryMapper::toCategoryDTO)
                                .toList())
                        .message("get categories successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CategoryDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        category = categoryService.create(category);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(categoryMapper.toCategoryDTO(category))
                        .message("create category successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(categoryMapper.toCategoryDTO(category))
                        .message("get category successfully")
                        .httpStatus(FOUND.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        category = categoryService.update(id, category);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(categoryMapper.toCategoryDTO(category))
                        .message("update category successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete category successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }
}
