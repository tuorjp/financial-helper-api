package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment save(Payment payment);

    @Query("SELECT p FROM payment p WHERE p.payment_date BETWEEN :startDate AND :endDate AND p.user_id = :userId")
    List<Payment> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") int userId);

    @Query("SELECT p FROM payment WHERE p.payment_category = :paymentCategory AND p.user_id = :userId")
    List<Payment> findByCategory(@Param("paymentCategory") int paymentCategory, @Param("userId") Integer userId);

    @Query("SELECT p FROM payment p WHERE p.payment_value >= :startValue AND p.payment_value <= :endValue AND p.user_id = :userId")
    List<Payment> findByValueBetween(@Param("startValue") float startValue, @Param("endValue") float endValue, @Param("userId") int userId);
}
