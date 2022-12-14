package com.dti.finalproject.group3.walletapi.dto;

import java.util.UUID;

import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
public class ApplicationUserRequestDTO {
    private UUID id;
    private String email;
    private String username;
    private String password;

    public ApplicationUser convertToEntity() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(this.password);

        return ApplicationUser.builder()
                    .id(this.id)
                    .email(this.email)
                    .username(this.username)
                    .password(encryptedPassword)
                    .build();
    }
}
