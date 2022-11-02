package com.dti.finalproject.group3.walletapi.customer;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUser;
import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUserService;
import com.dti.finalproject.group3.walletapi.wallet.IdNotMatchException;
import com.dti.finalproject.group3.walletapi.wallet.Wallet;

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
