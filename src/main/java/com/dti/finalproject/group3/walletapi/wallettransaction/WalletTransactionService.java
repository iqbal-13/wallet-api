package com.dti.finalproject.group3.walletapi.wallettransaction;

import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.transaction.Transaction;
import com.dti.finalproject.group3.walletapi.transaction.TransactionService;
import com.dti.finalproject.group3.walletapi.wallet.Wallet;
import com.dti.finalproject.group3.walletapi.wallet.WalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepository;
    private final WalletService walletService;
    private final TransactionService transactionService;

    public WalletTransaction createOne(WalletTransaction newWalletTransaction) {
        Wallet sourceWallet = this.walletService.findByCustomer();
        Long sourceBalance = sourceWallet.getBalance();
        Wallet destinationWallet = this.walletService.findById(newWalletTransaction.getWallet().getId());
        Transaction transaction = this.transactionService.create(newWalletTransaction.getTransaction());
        Long transferAmount = transaction.getAmount();
        if (sourceBalance < transferAmount) {
            throw new NotEnoughBalanceException();
        }

        sourceWallet.setBalance(sourceBalance - transferAmount);
        destinationWallet.setBalance(destinationWallet.getBalance() + transferAmount);
        newWalletTransaction.setWallet(destinationWallet);
        newWalletTransaction.setTransaction(transaction);

        return this.walletTransactionRepository.save(newWalletTransaction);
    }
}
