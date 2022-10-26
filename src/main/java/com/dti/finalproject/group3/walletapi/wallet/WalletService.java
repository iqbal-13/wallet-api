package com.dti.finalproject.group3.walletapi.wallet;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.applicationuser.UserPrincipal;
import com.dti.finalproject.group3.walletapi.customer.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet create(Wallet newWallet) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Customer customer = userPrincipal.getCustomer();
        newWallet.setCustomer(customer);
        Optional<Wallet> wallet = this.walletRepository.findByCustomer(customer);
        if (!wallet.isEmpty()) {
            throw new WalletAlreadyExistsException();
        }

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
}
