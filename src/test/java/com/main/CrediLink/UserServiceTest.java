package com.main.CrediLink;

import com.main.CrediLink.application.user.UserEntity;
import com.main.CrediLink.application.user.UserRepository;
import com.main.CrediLink.application.user.UserService;
import com.main.CrediLink.application.user.dto.ResponseUserDTO;
import com.main.CrediLink.application.user.enuns.StatusUser;
import com.main.CrediLink.application.user.enuns.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Lucas");
        user.setCpfCnpj("12345678909");
        user.setPhone("2728889090");
        user.setStatus(StatusUser.A);
        user.setRole(UserRole.ADMIN.getRole());

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        ResponseUserDTO result = userService.findById(1L);

        assertEquals("Lucas", result.name());
        verify(userRepository, times(1)).findById(1L);
    }
}
