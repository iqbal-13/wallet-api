package com.dti.finalproject.group3.walletapi.service;

import javax.transaction.Transactional;

import com.dti.finalproject.group3.walletapi.security.UserPrincipal;
import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import com.dti.finalproject.group3.walletapi.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.applicationUserRepository
            .findByUsername(username)
            .orElseThrow(()-> new UsernameNotFoundException("Username not found-> username or email: " + username));
        
        return UserPrincipal.create(applicationUser);
    }

    public void updateOne(ApplicationUser applicationUser) {
        this.applicationUserRepository.save(applicationUser);
    }
}
