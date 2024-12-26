package com.tuorjp.financial_helper.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 25)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}
