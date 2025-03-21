package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    List<Category> findByType(Integer type);
}
