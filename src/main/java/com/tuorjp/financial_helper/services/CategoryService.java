package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.enums.CategoryType;
import com.tuorjp.financial_helper.exception.CategoryNotFoundException;
import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.exception.InvalidCategoryTypeException;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException(name);
        }

        return category.get();
    }

    public Category createCategory(Category category) {
        if (!CategoryType.isValidType(category.getType())) {
            throw new InvalidCategoryTypeException("Invalid category type " + category.getType());
        }

        var existentCategory = categoryRepository.findByName(category.getName());

        if (existentCategory != null) {
            throw new DuplicatedTupleException("Category already exists");
        }

        return categoryRepository.save(category);
    }

    public List<Category> findByType(Integer type) {
        if (!CategoryType.isValidType(type)) {
            throw new InvalidCategoryTypeException("Invalid category type " + type);
        }
        Optional<List<Category>> categoryList = categoryRepository.findByType(type);

        if (categoryList.isEmpty()) {
            throw new CategoryNotFoundException("Categories not found for type " + type);
        }

        return categoryList.get();
    }

    public Category findById(int id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
