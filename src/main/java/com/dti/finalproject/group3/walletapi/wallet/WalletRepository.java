package com.dti.finalproject.group3.walletapi.wallet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dti.finalproject.group3.walletapi.customer.Customer;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCustomer(Customer customer);
}
