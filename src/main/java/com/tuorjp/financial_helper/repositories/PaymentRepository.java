package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment save(Payment payment);

    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate AND p.user = :userId")
    List<Payment> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") int userId);

    @Query("SELECT p FROM Payment p WHERE p.category = :paymentCategory AND p.user = :userId")
    List<Payment> findByCategory(@Param("paymentCategory") int paymentCategory, @Param("userId") Integer userId);

    @Query("SELECT p FROM Payment p WHERE p.paymentValue >= :startValue AND p.paymentValue <= :endValue AND p.user = :userId")
    List<Payment> findByPaymentValueBetween(@Param("startValue") float startValue, @Param("endValue") float endValue, @Param("userId") int userId);
}
