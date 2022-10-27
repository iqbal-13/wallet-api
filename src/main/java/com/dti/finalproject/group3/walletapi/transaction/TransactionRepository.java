package com.dti.finalproject.group3.walletapi.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query(value = "select * from transaction t where t.source_wallet_id = :id and t.amount between :minAmount and :maxAmount and t.created_at between :fromDate and :toDate", nativeQuery = true)
    List<Transaction> findByAmountOrDate(@Param("id") Long id, @Param("minAmount") Long minAmount,@Param("maxAmount") Long maxAmount,@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate);
}
