package com.example.AuthSecurityService.Repository;

import com.example.AuthSecurityService.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByIdIn(List<Long> roleId);
}
