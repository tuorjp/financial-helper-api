package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.PaymentDTO;
import com.tuorjp.financial_helper.dto.PaymentMapper;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.services.CategoryService;
import com.tuorjp.financial_helper.services.PaymentService;
import com.tuorjp.financial_helper.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final PaymentMapper paymentMapper;

    @PostMapping("/v1/payment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO dto) {
        try {

            Category category = categoryService.findById(dto.getCategory());
            User user = userService.findById(dto.getUser());
            Payment payment = paymentMapper.mapToPayment(dto, user, category);
            Payment createdPayment = paymentService.createPayment(payment);

            Map<String, Object> response = new HashMap<>();
            response.put("paymentDate", createdPayment.getPaymentDate());
            response.put("paymentValue", createdPayment.getPaymentValue());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Argument invalid: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Resource not found: " + e.getMessage());
        }
    }
}
