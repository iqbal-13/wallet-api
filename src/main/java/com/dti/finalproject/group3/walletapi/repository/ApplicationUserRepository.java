package com.dti.finalproject.group3.walletapi.repository;

import java.util.Optional;
import java.util.UUID;

import com.dti.finalproject.group3.walletapi.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByUsername(String username);
}
