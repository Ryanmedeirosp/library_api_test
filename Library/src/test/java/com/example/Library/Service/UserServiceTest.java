package com.example.Library.Service;

import com.example.Library.model.Dto.UserCreateDto;
import com.example.Library.model.User;
import com.example.Library.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Library.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração do usuário mockado
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
    }

    @Test
    public void testCreateUser() {
        // Criando UserCreateDto diretamente no teste
        UserCreateDto userCreateDto = new UserCreateDto("John Doe", "johndoe@example.com");

        // Simulando que o e-mail ainda não existe no repositório
        when(userRepository.existsByEmail(userCreateDto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(userCreateDto);

        // Verificar se o repositório salva o usuário corretamente
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserWithExistingEmail() {
        // Criando UserCreateDto diretamente no teste
        UserCreateDto userCreateDto = new UserCreateDto("John Doe", "johndoe@example.com");

        // Simulando que o e-mail já existe no repositório
        when(userRepository.existsByEmail(userCreateDto.getEmail())).thenReturn(true);

        // Espera-se que lance uma IllegalArgumentException devido ao e-mail existente
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userCreateDto));

        // Verificar que o método save não foi chamado
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1)).thenReturn(java.util.Optional.empty());

        User result = userService.getUserById(1);

        assertNull(result);
    }
}
