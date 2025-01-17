package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.dto.PaymentDTO;
import com.tuorjp.financial_helper.dto.ReceiptDTO;
import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReceiptDTO> findReceiptsWithinDates(LocalDate startDate, LocalDate endDate) {
        List<Receipt> receipts = receiptRepository.findByDateBetween(startDate, endDate, userService.getCurrentUser());

        return receipts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReceiptDTO> findReceiptsByCategory(int categoryId) {
        List<Receipt> receipts = receiptRepository.findByCategory(categoryId, userService.getCurrentUser());

        return receipts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReceiptDTO> findReceiptsBetweenValues(float startValue, float endValue) {
        List<Receipt> receipts = receiptRepository.findByPaymentValueBetween(startValue, endValue, userService.getCurrentUser());

        return receipts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReceiptDTO convertToDTO(Receipt receipt) {
        ReceiptDTO dto = new ReceiptDTO();
        dto.setReceiptDate(receipt.getReceiptDate());
        dto.setReceiptValue(receipt.getReceiptValue());
        dto.setCategory(receipt.getCategory().getId());
        dto.setUser(receipt.getUser().getId());
        return dto;
    }
}
