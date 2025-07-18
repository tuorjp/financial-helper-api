package com.tuorjp.financial_helper.dto;

import com.tuorjp.financial_helper.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public Category mapToCategory(CategoryDTO dto) {
    return Category
        .builder()
        .name(dto.getName())
        .type(dto.getType())
        .build();
  }
}
