package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Receipt;
import com.tuorjp.financial_helper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Receipt save(Receipt receipt);

    @Query("SELECT r FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate AND r.user = :user")
    List<Receipt> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("user") User user);

    @Query("SELECT r FROM Receipt r WHERE r.category = :receiptCategory AND r.user = :user")
    List<Receipt> findByCategory(@Param("receiptCategory") int categoryId, @Param("user") User user);

    @Query("SELECT r FROM Receipt r WHERE r.receiptValue >= :startValue AND r.receiptValue <= :endValue AND r.user = :user")
    List<Receipt> findByPaymentValueBetween(@Param("startValue") float startValue, @Param("endValue") float endValue, @Param("user") User userId);
}
