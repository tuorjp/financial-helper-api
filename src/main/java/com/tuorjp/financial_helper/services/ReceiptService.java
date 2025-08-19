package com.tuorjp.financial_helper.services;

import com.tuorjp.financial_helper.dto.ReceiptDTO;
import com.tuorjp.financial_helper.exception.InvalidDateArgumentException;
import com.tuorjp.financial_helper.exception.InvalidMonetaryValueException;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptService {
  private final ReceiptRepository receiptRepository;
  private final UserService userService;

  public Receipt createReceipt(Receipt receipt) {
    return receiptRepository.save(receipt);
  }

  public List<ReceiptDTO> findReceiptsWithinDates(LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null) {
      throw new InvalidDateArgumentException();
    }

    List<Receipt> receipts = receiptRepository
        .findByDateBetween(
            startDate,
            endDate,
            userService.getCurrentUser()
        );

    return receipts.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<ReceiptDTO> findReceiptsByCategory(Category category) {
    List<Receipt> receipts = receiptRepository.findByCategory(category, userService.getCurrentUser());

    return receipts.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<ReceiptDTO> findReceiptsBetweenValues(Float startValue, Float endValue) {
    if (startValue == null || endValue == null) {
      throw new InvalidMonetaryValueException();
    }

    List<Receipt> receipts = receiptRepository
        .findByReceiptValueBetween(
            startValue,
            endValue,
            userService.getCurrentUser()
        );

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
