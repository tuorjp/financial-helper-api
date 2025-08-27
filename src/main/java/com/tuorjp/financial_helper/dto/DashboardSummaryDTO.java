package com.tuorjp.financial_helper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummaryDTO {
    private long totalReceipts; // Total de entradas
    private long totalPayments; // Total de saídas
    private long totalReceiptCategories; // Total de categorias de entrada
    private long totalPaymentCategories; // Total de categorias de saída
}