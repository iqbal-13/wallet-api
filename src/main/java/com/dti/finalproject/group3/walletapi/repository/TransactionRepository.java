package com.dti.finalproject.group3.walletapi.repository;

import java.time.LocalDate;

import com.dti.finalproject.group3.walletapi.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query(value = "select * from transaction t where t.source_wallet_id = :id and t.amount between :minAmount and :maxAmount and t.created_at between :fromDate and :toDate", nativeQuery = true)
    Page<Transaction> findByTransactionTypeOutAndAmountOrDate(@Param("id") Long id,
                                         @Param("minAmount") Long minAmount,
                                         @Param("maxAmount") Long maxAmount,
                                         @Param("fromDate") LocalDate fromDate,
                                         @Param("toDate") LocalDate toDate,
                                         Pageable pageable);

    @Query(value = "select * from transaction t where t.destination_wallet_id = :id and t.amount between :minAmount and :maxAmount and t.created_at between :fromDate and :toDate", nativeQuery = true)
    Page<Transaction> findByTransactionTypeInAndAmountOrDate(@Param("id") Long id,
                                         @Param("minAmount") Long minAmount,
                                         @Param("maxAmount") Long maxAmount,
                                         @Param("fromDate") LocalDate fromDate,
                                         @Param("toDate") LocalDate toDate,
                                         Pageable pageable);
}
