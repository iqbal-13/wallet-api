package com.dti.finalproject.group3.walletapi.transaction;

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
public class TransactionRequestDTO {
    private Long id;
    private String description;
    private Long amount;
    private Long destinationWalletId;


    public Transaction convertToEntity() {
        Wallet destinationWallet = Wallet.builder().id(destinationWalletId).build();
        return Transaction.builder().id(this.id).description(this.description).amount(this.amount).destinationWallet(destinationWallet).build();
    }
}
