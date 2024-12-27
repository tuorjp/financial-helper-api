package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
