package com.vrvsecurity.rbacdemo.repository;

import com.vrvsecurity.rbacdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
