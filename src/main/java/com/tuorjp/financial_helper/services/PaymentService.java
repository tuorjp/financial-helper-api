package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        User currentUser = this.getCurrentUser();

        payment.setUser(currentUser);

        return paymentRepository.save(payment);
    }

    public List<Payment> findPaymentsWithinDates(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByDateBetween(startDate, endDate, this.getCurrentUserId());
    }

    public List<Payment> findPaymentsByCategory(int paymentCategory) {
        return paymentRepository.findByCategory(paymentCategory, this.getCurrentUserId());
    }

    public List<Payment> findPaymentBetweenValues(float startValue, float endValue) {
        int currentUserId = this.getCurrentUserId();

        return paymentRepository.findByPaymentValueBetween(startValue, endValue, currentUserId);
    }

    private Integer getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
