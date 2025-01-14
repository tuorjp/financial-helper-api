package com.tuorjp.financial_helper.repositories;

import com.tuorjp.financial_helper.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Receipt save(Receipt receipt);

    @Query("SELECT r FROM receipt r WHERE r.receipt_date BETWEEN :startDate AND :endDate AND r.user_id = :userId")
    List<Receipt> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") int userId);

    @Query("SELECT r FROM receipt r WHERE r.receipt_category = :receiptCategory AND r.user_id = :userId")
    List<Receipt> findByCategory(@Param("receiptCategory") int categoryId, @Param("userId") int userId);

    @Query("SELECT r FROM receipt r WHERE r.receipt_value >= :startValue AND r.receipt_value <= :endValue AND r.user_id = :userId")
    List<Receipt> findByValueBetween(@Param("startValue") float startValue, @Param("endValue") float endValue, @Param("userId") int userId);
}
