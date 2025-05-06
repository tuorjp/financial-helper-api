package com.tuorjp.financial_helper.controllers;

import com.tuorjp.financial_helper.dto.ReceiptDTO;
import com.tuorjp.financial_helper.dto.ReceiptMapper;
import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.models.User;
import com.tuorjp.financial_helper.services.CategoryService;
import com.tuorjp.financial_helper.services.ReceiptService;
import com.tuorjp.financial_helper.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ReceiptMapper receiptMapper;

    @PostMapping("/v1/receipt")
    public ResponseEntity<?> createReceipt(@RequestBody ReceiptDTO dto) {
        try {

            User user = userService.findById(dto.getUser());
            Category category = categoryService.findById(dto.getCategory());

            Receipt receipt = receiptMapper.mapToReceipt(dto, user, category);

            Receipt createdReceipt = receiptService.createReceipt(receipt);
            Map<String, Object> receiptResponse = new HashMap<>();
            receiptResponse.put("receiptValue", createdReceipt.getReceiptValue());
            receiptResponse.put("receiptDate", createdReceipt.getReceiptDate());

            return ResponseEntity.ok(receiptResponse);

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Resource not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Argument invalid: " + e.getMessage());
        }
    }

    @GetMapping("/v1/receipt")
    public ResponseEntity<?> getReceiptsBetweenValues(
            @RequestParam("init-value") Float initValue,
            @RequestParam("end-value") Float endValue
    ) {
        try {
            if (initValue == null || endValue == null) {
                return ResponseEntity.badRequest().body("init-value and end-value cannot be null or empty.");
            }

            List<ReceiptDTO> receipts = receiptService.findReceiptsBetweenValues(initValue, endValue);
            return ResponseEntity.ok(receipts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Argument invalid: " + e.getMessage());
        }
    }

    @GetMapping("/v1/receipt-by-date")
    public ResponseEntity<?> getReceiptsWithinDates(
            @RequestParam("init-date") LocalDate initDate,
            @RequestParam("end-date") LocalDate endDate
    ) {
        try {
            if (initDate == null || endDate == null) {
                return ResponseEntity.badRequest().body("init-date and end-date cannot be null or empty.");
            }

            List<ReceiptDTO> receipts = receiptService.findReceiptsWithinDates(initDate, endDate);
            return ResponseEntity.ok(receipts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid argument: " + e.getMessage());
        }
    }
}
