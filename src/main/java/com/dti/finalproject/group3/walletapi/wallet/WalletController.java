package com.dti.finalproject.group3.walletapi.wallet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createOne(@RequestBody WalletRequestDTO walletRequestDTO) {
        Wallet newWallet = walletRequestDTO.convertToEntity();
        Wallet savedWallet = this.walletService.create(newWallet);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);
    }

    @GetMapping("/wallets")
    public ResponseEntity<Wallet> getOne() {
        Wallet wallet = this.walletService.findByCustomer();

        return ResponseEntity.ok().body(wallet);
    }
}
