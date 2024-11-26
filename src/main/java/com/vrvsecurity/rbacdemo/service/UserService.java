package com.vrvsecurity.rbacdemo.service;

import com.vrvsecurity.rbacdemo.model.Role;
import com.vrvsecurity.rbacdemo.model.User;
import com.vrvsecurity.rbacdemo.repository.RoleRepository;
import com.vrvsecurity.rbacdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username, String password, Set<String> roles) {
        User user = new User(username, passwordEncoder.encode(password));
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role(roleName);
                roleRepository.save(role);
            }
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        return userRepository.save(user);
    }

    public Role createRole(String name) {
        Role role = new Role(name);
        return roleRepository.save(role);
    }
}
