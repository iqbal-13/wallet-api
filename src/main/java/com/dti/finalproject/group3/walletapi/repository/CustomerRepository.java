package com.dti.finalproject.group3.walletapi.repository;

import com.dti.finalproject.group3.walletapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
