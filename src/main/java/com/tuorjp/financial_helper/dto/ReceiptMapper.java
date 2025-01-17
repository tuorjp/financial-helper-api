package com.tuorjp.financial_helper.dto;

import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.models.User;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {
    public Receipt mapToReceipt(ReceiptDTO dto, User user, Category category) {
        return Receipt.builder()
                .receiptValue(dto.getReceiptValue())
                .receiptDate(dto.getReceiptDate())
                .user(user)
                .category(category)
                .build();
    }
}
