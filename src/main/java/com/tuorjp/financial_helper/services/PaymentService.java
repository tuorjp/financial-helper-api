package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.dto.PaymentDTO;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<PaymentDTO> findPaymentsWithinDates(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = paymentRepository.findByDateBetween(startDate, endDate, userService.getCurrentUser());

        return payments
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> findPaymentsByCategory(int paymentCategory) {
        Category category = categoryService.findById(paymentCategory);
        List<Payment> payments = paymentRepository.findByCategory(category, userService.getCurrentUser());

        return payments
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> findPaymentBetweenValues(float startValue, float endValue) {
        List<Payment> payments;

        payments = paymentRepository.findByPaymentValueBetween(startValue, endValue, userService.getCurrentUser());

        return payments
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentValue(payment.getPaymentValue());
        dto.setCategory(payment.getCategory().getId());
        dto.setUser(payment.getUser().getId());
        return dto;
    }
}
