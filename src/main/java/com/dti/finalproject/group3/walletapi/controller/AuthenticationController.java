package com.dti.finalproject.group3.walletapi.controller;

import com.dti.finalproject.group3.walletapi.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dti.finalproject.group3.walletapi.jwt.JWTProvider;
import com.dti.finalproject.group3.walletapi.jwt.JWTResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @PostMapping("/tokens")
    public ResponseEntity<JWTResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = this.jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTResponseDTO(jwtToken));
    }
}
