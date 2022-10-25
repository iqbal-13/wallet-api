package com.dti.finalproject.group3.walletapi.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createOne(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer newCustomer = customerRequestDTO.convertToEntity();
        Customer savedCustomer = this.customerService.create(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
}