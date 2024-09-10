package com.imti.UserRegistrationAndLoginSystem;

import com.imti.UserRegistrationAndLoginSystem.dto.RegisterRequest;
import com.imti.UserRegistrationAndLoginSystem.model.User;
import com.imti.UserRegistrationAndLoginSystem.repository.UserRepository;
import com.imti.UserRegistrationAndLoginSystem.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserRegistration_Success() {
        // Register DTO used for passing to the register method
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstName("imtiaz")
                .lastName("ahmad")
                .userName("imtiazahmad")
                .email("imtiaz@email.com")
                .password("imtiaz")
                .build();

        // Create the user object with the encoded password, return by the register method of succeeds
        User registerUser = User.builder()
                .firstName("imtiaz")
                .lastName("ahmad")
                .username("imtiazahmad")
                .email("imtiaz@email.com")
                .password("encodedPassword")
                .build();

        when(userRepository.findByUsername("imtiazahmad")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("imtiaz@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("imtiaz")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(registerUser);

        User user = userService.register(registerRequest);

        assertNotNull(user);
        assertEquals("imtiazahmad", user.getUsername());
        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUserRegistration_Username_Already_Exists() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstName("imtiaz")
                .lastName("ahmad")
                .userName("existingUser")
                .email("imtiaz@email.com")
                .password("imtiaz")
                .build();

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(registerRequest);
        });

        assertEquals("Username already exist.", exception.getMessage());

    }

    @Test
    void testUserRegistration_Email_Already_Exists() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstName("imtiaz")
                .lastName("ahmad")
                .userName("imtiazahmad")
                .email("existingEmail@email.com")
                .password("imtiaz")
                .build();

        when(userRepository.findByUsername("imtiazahmad")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("existingEmail@email.com")).thenReturn(Optional.of(new User()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           userService.register(registerRequest);
        });

        assertEquals("Email already exist.", exception.getMessage());
    }

    @Test
    void testFetchAllUsers_Success() {
        User user1 = User.builder()
                .firstName("Imtiaz")
                .lastName("Ahmad")
                .username("ImtiazAhmad")
                .email("imtiazahmad@gmail.com")
                .password("userpasswordencoded")
                .build();

        User user2 = User.builder()
                .firstName("Ahmad")
                .lastName("Adrali")
                .username("AhmadAdrali")
                .email("AhmadAdrali@gmail.com")
                .password("ahmadadraliencodedpassword")
                .build();

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> fetchUsers = userService.getUsers();

        assertNotNull(fetchUsers);
        assertEquals(2, fetchUsers.size());
        assertEquals("ImtiazAhmad", fetchUsers.get(0).getUsername());
        assertEquals("AhmadAdrali", fetchUsers.get(1).getUsername());

        verify(userRepository).findAll();

    }

    void testFetchAllUsers_Failure() {

    }

    @Test
    void testDeleteUser_Success() {
        Long id = 1L;

        userService.deleteUser(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void testDeleteUser_failure() {
        Long id = 1L;
        doThrow(new RuntimeException("User not found")).when(userRepository).deleteById(id);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(id);
        });

        assertEquals("User not found", exception.getMessage());

        verify(userRepository).deleteById(id);
    }

}
