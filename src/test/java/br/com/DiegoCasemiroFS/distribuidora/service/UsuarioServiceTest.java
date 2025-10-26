package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.User;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Test
    @DisplayName("Verifica se tem um Id válido")
    void teste01() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User resultado = userService.findById(id);

        assertNotNull(resultado);
        assertEquals(resultado, user);
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Retorna exception quando o Id não é encontrado")
    void teste02() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(id));
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Verifica se tem um email válido")
    void teste03() {
        String email = "teste@email.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User resultado = userService.findByEmail(email);

        assertNotNull(resultado);
        assertEquals(resultado, user);
        verify(userRepository).findByEmail(email);
    }

    @Test
    @DisplayName("Retorna exception quando o Email não é encontrado")
    void teste04() {
        String email = "teste@email.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findByEmail(email));
        verify(userRepository).findByEmail(email);
    }

}