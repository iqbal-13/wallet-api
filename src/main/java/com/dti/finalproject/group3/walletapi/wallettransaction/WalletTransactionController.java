package com.dti.finalproject.group3.walletapi.wallettransaction;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class WalletTransactionController {
    private final WalletTransactionService walletTransactionService;

    @PostMapping("/wallets/{id}/transactions")
    public ResponseEntity<WalletTransaction> createOne(@RequestBody WalletTransactionRequestDTO walletTransactionRequestDTO) {
        WalletTransaction newWalletTransaction = walletTransactionRequestDTO.convertToEntity();
        WalletTransaction savedWalletTransaction = this.walletTransactionService.createOne(newWalletTransaction);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWalletTransaction);
    }
    
}
