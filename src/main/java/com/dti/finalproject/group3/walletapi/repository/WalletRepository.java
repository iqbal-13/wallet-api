package com.dti.finalproject.group3.walletapi.repository;

import java.util.Optional;

import com.dti.finalproject.group3.walletapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dti.finalproject.group3.walletapi.model.Customer;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCustomer(Customer customer);
}
