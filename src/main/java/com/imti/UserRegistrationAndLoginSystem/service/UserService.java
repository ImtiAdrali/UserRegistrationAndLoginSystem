package com.imti.UserRegistrationAndLoginSystem.service;

import com.imti.UserRegistrationAndLoginSystem.dto.RegisterRequest;
import com.imti.UserRegistrationAndLoginSystem.enums.RoleType;
import com.imti.UserRegistrationAndLoginSystem.model.User;
import com.imti.UserRegistrationAndLoginSystem.repository.UserRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest registerRequest) {
        Optional<User> userByName = userRepository.findByUsername(registerRequest.getUserName());
        Optional<User> userByEmail =  userRepository.findByEmail(registerRequest.getEmail());

        if (userByName.isPresent()) {
            throw new IllegalArgumentException("Username already exist.");
        }

        if (userByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exist.");
        }

        User user = User.builder()
                .username(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .roles(Set.of(RoleType.ROLE_USER))
                .build();

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
