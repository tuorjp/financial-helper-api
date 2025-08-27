package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.dto.DashboardSummaryDTO;
import com.tuorjp.financial_helper.enums.CategoryType;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.repositories.CategoryRepository;
import com.tuorjp.financial_helper.repositories.PaymentRepository;
import com.tuorjp.financial_helper.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public DashboardSummaryDTO getSummary(Optional<LocalDate> startDateOpt, Optional<LocalDate> endDateOpt) {
        User currentUser = userService.getCurrentUser();

        long totalReceipts;
        long totalPayments;

        if (startDateOpt.isPresent() && endDateOpt.isPresent()) {
            totalReceipts = receiptRepository.countByUserAndReceiptDateBetween(currentUser, startDateOpt.get(), endDateOpt.get());
            totalPayments = paymentRepository.countByUserAndPaymentDateBetween(currentUser, startDateOpt.get(), endDateOpt.get());
        } else {
            totalReceipts = receiptRepository.countByUser(currentUser);
            totalPayments = paymentRepository.countByUser(currentUser);
        }

        long receiptCategories = categoryRepository.countByType(CategoryType.ENTRADA.getValue());
        long paymentCategories = categoryRepository.countByType(CategoryType.SAIDA.getValue());

        return DashboardSummaryDTO.builder()
                .totalReceipts(totalReceipts)
                .totalPayments(totalPayments)
                .totalReceiptCategories(receiptCategories)
                .totalPaymentCategories(paymentCategories)
                .build();
    }
}