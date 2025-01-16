package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Category;
import com.tuorjp.financial_helper.models.Payment;
import com.tuorjp.financial_helper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment save(Payment payment);

    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate AND p.user = :user")
    List<Payment> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("user") User user);

    @Query("SELECT p FROM Payment p WHERE p.category = :category AND p.user = :user")
    List<Payment> findByCategory(@Param("category") Category category, @Param("user") User user);

    @Query("SELECT p FROM Payment p WHERE p.paymentValue >= :startValue AND p.paymentValue <= :endValue AND p.user = :user")
    List<Payment> findByPaymentValueBetween(@Param("startValue") float startValue, @Param("endValue") float endValue, @Param("user") User user);
}
