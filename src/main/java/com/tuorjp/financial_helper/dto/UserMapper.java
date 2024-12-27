package com.tuorjp.financial_helper.dto;

import com.tuorjp.financial_helper.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserDTO dto) {
        return User
                .builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }
}
