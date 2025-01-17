package com.tuorjp.financial_helper.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceiptDTO {
    private LocalDate receiptDate;
    private float receiptValue;
    private int category;
    private int user;
}
