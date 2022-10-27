package com.dti.finalproject.group3.walletapi.wallet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.dti.finalproject.group3.walletapi.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(100000)
    private Long balance;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    //TODO add wallet tag?

    public WalletResponseDTO convertToDTO() {
        return WalletResponseDTO.builder().id(this.id).build();
    }
}
