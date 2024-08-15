package com.example.AuthSecurityService.security.Repository;

import java.util.Optional;


import com.example.AuthSecurityService.security.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}