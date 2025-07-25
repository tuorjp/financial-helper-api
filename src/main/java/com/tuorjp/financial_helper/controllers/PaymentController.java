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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;
  private final CategoryService categoryService;
  private final UserService userService;
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

  @GetMapping("/v1/payment")
  public ResponseEntity<?> getPaymentsBetweenValues(
      @RequestParam(name = "init-value") Float initValue,
      @RequestParam(name = "end-value") Float endValue
  ) {
    if (initValue == null || endValue == null) {
      return ResponseEntity.badRequest().body("init-value and end-value cannot be null or empty.");
    }

    List<PaymentDTO> payments;
    try {
      payments = paymentService.findPaymentBetweenValues(initValue, endValue);
      return ResponseEntity.ok(payments);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving payments: " + e.getMessage());
    }
  }

  @GetMapping("/v1/payment-by-date")
  public ResponseEntity<?> getPaymentsWithinDates(
      @RequestParam(name = "init-date") LocalDate initDate,
      @RequestParam(name = "end-date") LocalDate endDate
  ) {
    if (initDate == null || endDate == null) {
      return ResponseEntity.badRequest().body("init-date and end-date cannot be null or empty.");
    }

    List<PaymentDTO> payments;
    try {
      payments = paymentService.findPaymentsWithinDates(initDate, endDate);
      return ResponseEntity.ok(payments);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving payments: " + e.getMessage());
    }
  }
}
