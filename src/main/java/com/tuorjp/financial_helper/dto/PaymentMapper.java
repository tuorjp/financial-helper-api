package com.tuorjp.financial_helper.dto;

import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.models.User;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment mapToPayment(PaymentDTO dto, User user, Category category) {
        return Payment.builder()
                .paymentDate(dto.getPaymentDate())
                .paymentValue(dto.getPaymentValue())
                .user(user)
                .category(category)
                .build();
    }
}
