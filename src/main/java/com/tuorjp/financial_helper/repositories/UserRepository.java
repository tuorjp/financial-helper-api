package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User save(User user);
}
