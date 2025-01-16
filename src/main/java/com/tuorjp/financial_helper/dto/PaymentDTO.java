package com.tuorjp.financial_helper.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {
    private LocalDate paymentDate;
    private float paymentValue;
    private int category;
    private int user;
}
