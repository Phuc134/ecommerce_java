package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dto.request.CategoryRequest;
import org.example.ecommerce.dto.response.ApiResponse;
import org.example.ecommerce.dto.response.CategoryResponse;
import org.example.ecommerce.repository.CategoryRepository;
import org.example.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return
                ApiResponse.<List<CategoryResponse>>builder()
                        .result(categoryService.getAll())
                        .build();
    }
    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest category) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.create(category))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest category) {
        return
                ApiResponse.<CategoryResponse>builder()
                        .result(categoryService.update(id, category))
                        .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);
        return ApiResponse.<String>builder()
                .result("Category deleted")
                .build();
    }

}
