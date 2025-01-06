package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.CategoryDTO;
import com.tuorjp.financial_helper.dto.CategoryMapper;
import com.tuorjp.financial_helper.enums.CategoryType;
import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/v1/category")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.createCategory(categoryMapper.mapToCategory(categoryDTO));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DuplicatedTupleException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error creating category " + e.getMessage());
        }
    }

    @GetMapping("/v1/category/{type}")
    public ResponseEntity<?> getCategoriesByType(@PathVariable String type) {
        try {
            List<Category> categories = categoryService.findByType(Integer.getInteger(type));

            if (categories.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(categories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid category type " + e.getMessage());
        }
    }
}
