package com.dti.finalproject.group3.walletapi.customer;

import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUser;
import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationUserService applicationUserService;

    public Customer create(Customer newCustomer) {
        Customer savedCustomer = this.customerRepository.save(newCustomer);
        ApplicationUser applicationUser = savedCustomer.getApplicationUser();
        applicationUser.setCustomer(savedCustomer);
        this.applicationUserService.updateOne(applicationUser);

        return savedCustomer;
    }

}
