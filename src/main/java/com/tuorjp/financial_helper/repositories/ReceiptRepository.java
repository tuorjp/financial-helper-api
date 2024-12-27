package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
