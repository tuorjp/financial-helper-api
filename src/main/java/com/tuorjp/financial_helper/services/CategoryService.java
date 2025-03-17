package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.enums.CategoryType;
import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category createCategory(Category category) {
        if (!CategoryType.isValidType(category.getType())) {
            throw new IllegalArgumentException("Invalid category type " + category.getType());
        }

        var existentCategory = categoryRepository.findByName(category.getName());

        if (existentCategory != null) {
            throw new DuplicatedTupleException("Category already exists");
        }

        return categoryRepository.save(category);
    }

    public List<Category> findByType(Integer type) {
        if (!CategoryType.isValidType(type)) {
            throw new IllegalArgumentException("Invalid category type " + type);
        }
        return categoryRepository.findByType(type);
    }

    public Category findById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID " + id));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
