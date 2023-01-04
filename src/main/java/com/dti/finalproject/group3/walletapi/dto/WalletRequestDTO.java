package com.dti.finalproject.group3.walletapi.dto;

import com.dti.finalproject.group3.walletapi.model.Wallet;
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
public class WalletRequestDTO {
    private Long id;
    private Long balance;

    public Wallet convertToEntity() {
        return Wallet.builder().id(this.id).balance(this.balance).build();
    }
}
