package com.example.AuthSecurityService.Service;

import com.example.AuthSecurityService.Model.Role;
import com.example.AuthSecurityService.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public Role createRole(String name) {
        Role role = new Role();

        return roleRepository.save(role);
    }
}
