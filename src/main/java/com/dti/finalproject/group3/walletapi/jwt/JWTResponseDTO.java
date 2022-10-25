package com.dti.finalproject.group3.walletapi.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponseDTO {
    private String token;
    private String type = "bearer";
    
    public JWTResponseDTO(String token) {
        this.token = token;
    }
}
