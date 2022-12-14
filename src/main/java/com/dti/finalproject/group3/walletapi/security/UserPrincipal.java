package com.dti.finalproject.group3.walletapi.security;

import java.util.Collection;
import java.util.UUID;

import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dti.finalproject.group3.walletapi.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Customer customer;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(ApplicationUser applicationUser) {
        return UserPrincipal.builder()
                .id(applicationUser.getId())
                .username(applicationUser.getUsername())
                .email(applicationUser.getEmail())
                .password(applicationUser.getPassword())
                .customer(applicationUser.getCustomer())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities;}

    @Override
    public String getPassword() { return this.password; }

    @Override
    public String getUsername() { return this.username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }
    
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
    
}
