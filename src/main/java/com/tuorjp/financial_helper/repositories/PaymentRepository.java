package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
