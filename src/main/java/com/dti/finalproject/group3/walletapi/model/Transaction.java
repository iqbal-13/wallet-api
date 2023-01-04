package com.dti.finalproject.group3.walletapi.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.dti.finalproject.group3.walletapi.dto.TransactionResponseDTO;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long amount;

    @OneToOne
    private Wallet sourceWallet;

    @OneToOne
    private Wallet destinationWallet;
    
    @CreationTimestamp
    private Timestamp createdAt;

    public TransactionResponseDTO convertToDTO() {
        return TransactionResponseDTO.builder()
                                     .id(this.id)
                                     .description(this.description)
                                     .amount(this.amount)
                                     .sourceWalletId(this.sourceWallet.getId())
                                     .destinationWalletId(this.destinationWallet.getId())
                                     .createdAt(this.createdAt)
                                     .build();
    }
}
