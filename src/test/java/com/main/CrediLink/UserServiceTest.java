package com.main.CrediLink;

import com.main.CrediLink.application.user.UserEntity;
import com.main.CrediLink.application.user.UserRepository;
import com.main.CrediLink.application.user.UserService;
import com.main.CrediLink.application.user.dto.ResponseUserDTO;
import com.main.CrediLink.application.user.enuns.StatusUser;
import com.main.CrediLink.application.user.enuns.UserRole;
import com.main.CrediLink.application.user.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Lucas");
        user.setCpfCnpj("12345678909");
        user.setPhone("2728889090");
        user.setStatus(StatusUser.A);
        user.setRole(UserRole.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        ResponseUserDTO result = userService.findById(1L);

        assertEquals("Lucas", result.name());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarErroAoBuscarUsuarioPorId() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                UserException.class,
                () -> userService.findById(1L),
                "Deve lançar exceção quando o usuário não é encontrado"
        );

        assertTrue(exception.getMessage().contains("Usuario Não encontrado"));
        verify(userRepository, times(1)).findById(1L);
    }
}
