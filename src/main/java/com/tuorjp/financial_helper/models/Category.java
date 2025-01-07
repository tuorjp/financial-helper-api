package com.tuorjp.financial_helper.models;

import com.tuorjp.financial_helper.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer type;

    @PrePersist
    @PreUpdate
    private void validateType() {
        if(!CategoryType.isValidType(this.type)) {
            throw new IllegalArgumentException("Invalid category type " + this.type);
        }
    }
}
