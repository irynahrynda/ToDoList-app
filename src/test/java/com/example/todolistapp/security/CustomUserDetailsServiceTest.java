package com.example.todolistapp.security;

import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.User;
import com.example.todolistapp.service.UserService;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailsServiceTest {
    private static final Role.RoleName ROLE_USER = Role.RoleName.USER;
    private static final String EMAIL = "bob@gmail.com";
    private static final String PASSWORD = "12345678";
    private static User user;
    private static Role role;
    private CustomUserDetailsService customUserDetailsService;
    private UserService userService;

    @BeforeAll
    static void beforeAll() {
        user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        role = new Role(ROLE_USER);
        user.setRoles(Set.of(role));
    }

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        customUserDetailsService = new CustomUserDetailsService(userService);
    }

    @Test
    void loadUserByUsername_Ok() {
        Mockito.when(userService.getUserByEmail(EMAIL)).thenReturn(Optional.ofNullable(user));
        UserDetails actual = customUserDetailsService.loadUserByUsername(EMAIL);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EMAIL, actual.getUsername());
        Assertions.assertEquals(PASSWORD, actual.getPassword());
    }

    @Test
    void loadUserByUsername_UserNameNotFound() {
        try {
            customUserDetailsService.loadUserByUsername(EMAIL);
        } catch (UsernameNotFoundException e) {
            Assertions.assertEquals("User not found", e.getMessage());
        }
    }
}
