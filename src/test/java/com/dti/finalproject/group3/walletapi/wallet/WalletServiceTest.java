package com.dti.finalproject.group3.walletapi.wallet;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUser;
import com.dti.finalproject.group3.walletapi.applicationuser.ApplicationUserService;
import com.dti.finalproject.group3.walletapi.applicationuser.UserPrincipal;
import com.dti.finalproject.group3.walletapi.customer.Customer;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {
    @Mock
    private WalletRepository walletRepository;

    @Mock
    private ApplicationUserService applicationUserService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private ApplicationUser applicationUser;

    @Mock
    private UserPrincipal userPrincipal;

    @InjectMocks
    private WalletService walletService;

    @Test
    void createOne_shouldSaveNewWalletForActiveCustomer_whenGivenBalanceIs100000() {
        Customer customer = Customer.builder().id(1L).name("test").nik(12345L).dateOfBirth(LocalDate.of(1990, 01, 01)).applicationUser(applicationUser).build();
        Wallet wallet = Wallet.builder().balance(100000L).customer(customer).build();
        Wallet savedWallet = Wallet.builder().id(1L).balance(100000L).customer(customer).build();
        Optional<Wallet> optionalWallet = Optional.empty();
        Mockito.when(this.securityContext.getAuthentication()).thenReturn(this.authentication);
        SecurityContextHolder.setContext(this.securityContext);
        Mockito.when(this.authentication.getPrincipal()).thenReturn(this.userPrincipal);
        Mockito.when(this.userPrincipal.getCustomer()).thenReturn(customer);
        Mockito.when(this.walletRepository.findByCustomer(customer)).thenReturn(optionalWallet);
        Mockito.when(this.walletRepository.save(wallet)).thenReturn(savedWallet);

        Wallet actualWallet = this.walletService.create(wallet);

        Assertions.assertEquals(savedWallet, actualWallet);
        Mockito.verify(this.securityContext, Mockito.times(1)).getAuthentication();
        Mockito.verify(this.authentication, Mockito.times(1)).getPrincipal();
        Mockito.verify(this.userPrincipal, Mockito.times(1)).getCustomer();
        Mockito.verify(this.walletRepository, Mockito.times(1)).findByCustomer(customer);
        Mockito.verify(this.walletRepository, Mockito.times(1)).save(wallet);
    }

    @Test
    void createOne_shouldThrowMinBalanceIs100000Exception_whenGivenBalanceIs10000() {
        Customer customer = Customer.builder().id(1L).name("test").nik(12345L).dateOfBirth(LocalDate.of(1990, 01, 01)).applicationUser(applicationUser).build();
        Wallet wallet = Wallet.builder().balance(10000L).customer(customer).build();
        Optional<Wallet> optionalWallet = Optional.empty();
        Mockito.when(this.securityContext.getAuthentication()).thenReturn(this.authentication);
        SecurityContextHolder.setContext(this.securityContext);
        Mockito.when(this.authentication.getPrincipal()).thenReturn(this.userPrincipal);
        Mockito.when(this.userPrincipal.getCustomer()).thenReturn(customer);
        Mockito.when(this.walletRepository.findByCustomer(customer)).thenReturn(optionalWallet);
        
        Assertions.assertThrows(MinBalanceIs100000Exception.class, ()-> this.walletService.create(wallet));
        Mockito.verify(this.securityContext, Mockito.times(1)).getAuthentication();
        Mockito.verify(this.authentication, Mockito.times(1)).getPrincipal();
        Mockito.verify(this.userPrincipal, Mockito.times(1)).getCustomer();
        Mockito.verify(this.walletRepository, Mockito.times(1)).findByCustomer(customer);
    }
}
