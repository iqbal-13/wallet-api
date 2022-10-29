package com.dti.finalproject.group3.walletapi.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dti.finalproject.group3.walletapi.applicationuser.UserPrincipal;
import com.dti.finalproject.group3.walletapi.wallet.IdNotMatchException;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createOne(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer newCustomer = customerRequestDTO.convertToEntity();
        Customer savedCustomer = this.customerService.create(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getOne(@PathVariable("id") Long id) {
        Customer existingCustomer = this.customerService.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!id.equals(userPrincipal.getCustomer().getId())) {
            throw new IdNotMatchException();
        }

        return ResponseEntity.ok().body(existingCustomer);
    }
}
