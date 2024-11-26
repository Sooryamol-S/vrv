package com.vrvsecurity.rbacdemo.repository;

import com.vrvsecurity.rbacdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
