package com.dti.finalproject.group3.walletapi.wallet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Wallet> getOne(@PathVariable("id") Long id) {
        Wallet wallet = this.walletService.findByCustomer();
        if (!id.equals(wallet.getId())) {
            throw new IdNotMatchException();
        }

        return ResponseEntity.ok().body(wallet);
    }

    @PutMapping("/wallets/{id}")
    public ResponseEntity<Wallet> topUpBalance(@PathVariable("id") Long id, @RequestBody WalletRequestDTO walletRequestDTO) {
        if (!id.equals(walletRequestDTO.getId())) {
            throw new IdNotMatchException();
        }

        Wallet newWallet = walletRequestDTO.convertToEntity();
        Wallet updatedWallet = this.walletService.topUpBalance(newWallet.getBalance());
        return ResponseEntity.ok().body(updatedWallet);
    }
}
