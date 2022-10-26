package com.dti.finalproject.group3.walletapi.transaction;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction create(Transaction newTransaction) {

        return this.transactionRepository.save(newTransaction);
    }
    
}
