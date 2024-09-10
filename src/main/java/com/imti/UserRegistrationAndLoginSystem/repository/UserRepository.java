package com.imti.UserRegistrationAndLoginSystem.repository;

import com.imti.UserRegistrationAndLoginSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
}
