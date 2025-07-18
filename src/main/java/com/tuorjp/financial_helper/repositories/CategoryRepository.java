package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<Category> findByName(String name);

  Optional<List<Category>> findByType(Integer type);
}
