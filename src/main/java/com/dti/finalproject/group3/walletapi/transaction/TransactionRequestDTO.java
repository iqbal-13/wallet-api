package com.dti.finalproject.group3.walletapi.transaction;

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

    public Transaction convertToEntity() {
        return Transaction.builder().id(this.id).description(this.description).amount(this.amount).build();
    }
}
