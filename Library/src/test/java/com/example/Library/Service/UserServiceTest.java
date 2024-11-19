package com.example.Library.Service;

import java.util.Arrays;
import java.util.List;

import com.example.Library.model.User;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setName("User 1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User 2");
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getName());
        assertEquals("User 2", result.get(1).getName());

        // Verificar se o método findAll foi chamado
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser_Valid() {
        // Arrange
        User user = new User();
        user.setName("User 1");
        user.setEmail("user1@example.com");

        // Simulando que o email não existe no repositório
        when(userRepository.existsByEmail("user1@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.createUser(user);

        // Assert
        verify(userRepository, times(1)).save(any(User.class)); // Verifica se o método save foi chamado
    }

    @Test
    void testCreateUser_Invalid_EmailExists() {
        // Arrange
        User user = new User();
        user.setName("User 1");
        user.setEmail("user1@example.com");

        // Simulando que o email já existe no repositório
        when(userRepository.existsByEmail("user1@example.com")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        

        // Verificar que o método save não foi chamado
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setName("User 1");
        user.setEmail("user1@example.com");

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        // Act
        User result = userService.getUserById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("User 1", result.getName());

        // Verificar se o método findById foi chamado
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(java.util.Optional.empty());

        // Act
        User result = userService.getUserById(1);

        // Assert
        assertNull(result);

        // Verificar se o método findById foi chamado
        verify(userRepository, times(1)).findById(1);
    }
}
