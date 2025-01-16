package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    UserService userService;

    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public List<Receipt> findReceiptsWithinDates(LocalDate startDate, LocalDate endDate) {
        return receiptRepository.findByDateBetween(startDate, endDate, userService.getCurrentUser());
    }

    public List<Receipt> findReceiptsByCategory(int categoryId) {
        return receiptRepository.findByCategory(categoryId, userService.getCurrentUser());
    }

    public List<Receipt> findReceiptsBetweenValues(float startValue, float endValue) {
        return receiptRepository.findByPaymentValueBetween(startValue, endValue, userService.getCurrentUser());
    }
}
