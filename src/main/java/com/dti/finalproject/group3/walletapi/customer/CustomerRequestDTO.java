package com.dti.finalproject.group3.walletapi.customer;

import java.time.LocalDate;

import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUser;
import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUserRequestDTO;

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
public class CustomerRequestDTO {
    private Long id;
    private String name;
    private Long nik;
    private LocalDate dateOfBirth;
    private ApplicationUserRequestDTO applicationUserRequestDTO;

    public Customer convertToEntity() {
        ApplicationUser applicationUser = this.applicationUserRequestDTO.convertToEntity();
        return Customer.builder()
                       .id(this.id)
                       .name(this.name)
                       .nik(this.nik)
                       .dateOfBirth(this.dateOfBirth)
                       .applicationUser(applicationUser)
                       .build();
    }
}
