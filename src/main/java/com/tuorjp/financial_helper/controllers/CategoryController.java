package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.CategoryDTO;
import com.tuorjp.financial_helper.dto.CategoryMapper;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/v1/category")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryMapper.mapToCategory(categoryDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/category")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/v1/category/{type}")
    public ResponseEntity<?> getCategoriesByType(@PathVariable String type) {
        List<Category> categories = categoryService.findByType(Integer.parseInt(type));
        return ResponseEntity.ok(categories);
    }
}
