package com.dti.finalproject.group3.walletapi.transaction;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.applicationuser.UserPrincipal;
import com.dti.finalproject.group3.walletapi.wallet.Wallet;
import com.dti.finalproject.group3.walletapi.wallet.WalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;

    public Transaction create(Transaction newTransaction) {
        Wallet sourceWallet = this.walletService.findByCustomer();
        Long sourceBalance = sourceWallet.getBalance();
        Wallet destinationWallet = this.walletService.findById(newTransaction.getDestinationWallet().getId());
        Long transferAmount = newTransaction.getAmount();
        if (sourceBalance < transferAmount) {
            throw new NotEnoughBalanceException();
        }

        sourceWallet.setBalance(sourceBalance - transferAmount);
        destinationWallet.setBalance(destinationWallet.getBalance() + transferAmount);
        newTransaction.setSourceWallet(sourceWallet);

        return this.transactionRepository.save(newTransaction);
    }

    public Page<Transaction> getAllTransactionFromCustomer(String transactionType, Long minAmount, Long maxAmount, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long walletId = userPrincipal.getCustomer().getWallet().getId();
        
        if (transactionType.equalsIgnoreCase("in")) {
            return this.transactionRepository.findByTransactionTypeInAndAmountOrDate(walletId, minAmount, maxAmount, fromDate, toDate, pageable);    
        }

        return this.transactionRepository.findByTransactionTypeOutAndAmountOrDate(walletId, minAmount, maxAmount, fromDate, toDate, pageable);
    }
}
