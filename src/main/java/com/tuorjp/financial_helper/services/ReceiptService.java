package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    public Receipt createReceipt(Receipt receipt) {
        User currentUser = this.getCurrentUser();

        receipt.setUser(currentUser);

        return receiptRepository.save(receipt);
    }

    public List<Receipt> findReceiptsWithinDates(LocalDate startDate, LocalDate endDate) {
        return receiptRepository.findByDateBetween(startDate, endDate, this.getCurrentUserId());
    }

    public List<Receipt> findReceiptsByCategory(int categoryId) {
        return receiptRepository.findByCategory(categoryId, this.getCurrentUserId());
    }

    public List<Receipt> findReceiptsBetweenValues(float startValue, float endValue) {
        int currentUserId = this.getCurrentUserId();
        return receiptRepository.findByValueBetween(startValue, endValue, currentUserId);
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
