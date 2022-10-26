package com.dti.finalproject.group3.walletapi.customer;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUser;
import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUserService;
import com.dti.finalproject.group3.walletapi.applicationuser.UserPrincipal;

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

    public Customer getOne() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        return userPrincipal.getCustomer();
    }

}
