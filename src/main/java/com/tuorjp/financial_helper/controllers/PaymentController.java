package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.PaymentDTO;
import com.tuorjp.financial_helper.dto.PaymentMapper;
import com.tuorjp.financial_helper.exception.InvalidDateArgumentException;
import com.tuorjp.financial_helper.exception.InvalidMonetaryValueException;
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
    Category category = categoryService.findById(dto.getCategory());
    User user = userService.findById(dto.getUser());
    Payment payment = paymentMapper.mapToPayment(dto, user, category);
    Payment createdPayment = paymentService.createPayment(payment);

    Map<String, Object> response = new HashMap<>();
    response.put("paymentDate", createdPayment.getPaymentDate());
    response.put("paymentValue", createdPayment.getPaymentValue());

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/v1/payment")
  public ResponseEntity<?> getPaymentsBetweenValues(
      @RequestParam(name = "init-value") Float initValue,
      @RequestParam(name = "end-value") Float endValue
  ) {
    List<PaymentDTO> payments;
    payments = paymentService.findPaymentBetweenValues(initValue, endValue);
    return ResponseEntity.ok(payments);
  }

  @GetMapping("/v1/payment-by-date")
  public ResponseEntity<?> getPaymentsWithinDates(
      @RequestParam(name = "init-date") LocalDate initDate,
      @RequestParam(name = "end-date") LocalDate endDate
  ) {
    List<PaymentDTO> payments;
      payments = paymentService.findPaymentsWithinDates(initDate, endDate);
      return ResponseEntity.ok(payments);
  }
}
