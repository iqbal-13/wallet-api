package com.dti.finalproject.group3.walletapi.customer;

import java.time.LocalDate;
import java.util.Optional;

import com.dti.finalproject.group3.walletapi.model.Customer;
import com.dti.finalproject.group3.walletapi.repository.CustomerRepository;
import com.dti.finalproject.group3.walletapi.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import com.dti.finalproject.group3.walletapi.service.ApplicationUserService;
import com.dti.finalproject.group3.walletapi.controller.IdNotMatchException;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ApplicationUserService applicationUserService;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createOne_shouldSaveNewCustomerNamedBale_whenGivenNameIsBale() {
        ApplicationUser applicationUser = ApplicationUser.builder()
                .email("test@gmail.com")
                .username("bale")
                .password("password")
                .build();
        Customer customer = Customer.builder()
            .name("Bale")
            .dateOfBirth(LocalDate.of(1990, 01, 01))
            .nik(12345L)
            .applicationUser(applicationUser)
            .build();
        Customer expectedResult = Customer.builder()
            .id(1L)
            .name("Bale")
            .dateOfBirth(LocalDate.of(1990, 01, 01))
            .nik(12345L)
            .applicationUser(applicationUser)
            .build();
        Mockito.when(this.customerRepository.save(customer)).thenReturn(expectedResult);

        Customer actualResult = this.customerService.create(customer);

        Assertions.assertEquals(expectedResult, actualResult);
        Mockito.verify(this.customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void findById_shouldReturnCustomerNamedBale_whenGivenIdIs1() {
        ApplicationUser applicationUser = ApplicationUser.builder()
                .email("test@gmail.com")
                .username("bale")
                .password("password")
                .build();
        Customer customer = Customer.builder()
            .id(1L)
            .name("Bale")
            .dateOfBirth(LocalDate.of(1990, 01, 01))
            .nik(12345L)
            .applicationUser(applicationUser)
            .build();
        Optional<Customer> optionalCustomer = Optional.of(customer);
        Mockito.when(this.customerRepository.findById(1L)).thenReturn(optionalCustomer);

        Customer actualResult = this.customerService.findById(1L);

        Assertions.assertEquals(customer, actualResult);
        Mockito.verify(this.customerRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowIdNotMatchException_whenGivenIdIs1() {
        Optional<Customer> optionalCustomer = Optional.empty();
        Mockito.when(this.customerRepository.findById(1L)).thenReturn(optionalCustomer);

        Assertions.assertThrows(IdNotMatchException.class, ()-> this.customerService.findById(1L));
        Mockito.verify(this.customerRepository, Mockito.times(1)).findById(1L);
    }
}
