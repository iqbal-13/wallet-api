package com.dti.finalproject.group3.walletapi.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private String description;
    private Long amount;
    private Long sourceWalletId;
    private Long destinationWalletId;
    private Timestamp createdAt;
}
