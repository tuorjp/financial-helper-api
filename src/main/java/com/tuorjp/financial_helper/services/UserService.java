package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        var existentUser = userRepository.findByEmail(user.getEmail());

        if(existentUser != null) {
            throw new DuplicatedTupleException("User already exists");
        }

        encodePassword(user);
        return userRepository.save(user);
    }

    public void encodePassword(User user) {
        String rawPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptedPassword);
    }

    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID " + id));
    }
}
