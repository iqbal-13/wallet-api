package com.dti.finalproject.group3.walletapi.service;

import java.util.Optional;

import com.dti.finalproject.group3.walletapi.model.Wallet;
import com.dti.finalproject.group3.walletapi.repository.WalletRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet create(Wallet newWallet) {
        if (newWallet.getBalance() < 100000) {
            throw new MinBalanceIs100000Exception();
        }
        return this.walletRepository.save(newWallet);
    }

    public Wallet findByCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Optional<Wallet> optionalWallet = this.walletRepository.findByCustomer(userPrincipal.getCustomer());
        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException();
        }

        return optionalWallet.get();
    }

    public Wallet findById(Long id) {
        Optional<Wallet> optionalWallet = this.walletRepository.findById(id);
        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException();
        }

        return optionalWallet.get();
    }

    public Wallet topUpBalance(Long amount) {
        Wallet wallet = this.findByCustomer();
        wallet.setBalance(wallet.getBalance() + amount);

        return this.walletRepository.save(wallet);
    }
}
