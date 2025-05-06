package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.UserDTO;
import com.tuorjp.financial_helper.dto.UserMapper;
import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/v1/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userMapper.mapToUser(userDTO));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DuplicatedTupleException e) {
            return ResponseEntity.badRequest().body("Error creating user " + e.getMessage());
        }
    }
}
