package com.dti.finalproject.group3.walletapi.service;

import java.util.Optional;

import com.dti.finalproject.group3.walletapi.model.Customer;
import com.dti.finalproject.group3.walletapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import com.dti.finalproject.group3.walletapi.controller.IdNotMatchException;
import com.dti.finalproject.group3.walletapi.model.Wallet;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationUserService applicationUserService;

    public Customer create(Customer newCustomer) {
        Customer savedCustomer = this.customerRepository.save(newCustomer);
        Wallet wallet = savedCustomer.getWallet();
        wallet.setCustomer(savedCustomer);
        ApplicationUser applicationUser = savedCustomer.getApplicationUser();
        applicationUser.setCustomer(savedCustomer);
        this.applicationUserService.updateOne(applicationUser);

        return savedCustomer;
    }

    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new IdNotMatchException();
        }

        return optionalCustomer.get();
    }

}
