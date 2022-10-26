package com.dti.finalproject.group3.walletapi.wallettransaction;

import com.dti.finalproject.group3.walletapi.transaction.Transaction;
import com.dti.finalproject.group3.walletapi.transaction.TransactionRequestDTO;
import com.dti.finalproject.group3.walletapi.wallet.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionRequestDTO {
    private Long id;
    private Long walletId;
    private TransactionRequestDTO transactionRequestDTO;

    public WalletTransaction convertToEntity() {
        Wallet wallet = Wallet.builder().id(this.walletId).build();
        Transaction transaction = transactionRequestDTO.convertToEntity();

        return WalletTransaction.builder().id(this.id).wallet(wallet).transaction(transaction).build();
    }
}
