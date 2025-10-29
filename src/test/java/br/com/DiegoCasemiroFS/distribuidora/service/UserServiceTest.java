package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.users.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.UserResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.LoginNotSuccessfulException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.LoginException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private UserRequestDto  userRequestDto;

    @Test
    @DisplayName("Verifica se o Usuário tem um Id válido")
    void teste01() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User resultado = userService.findById(id);

        assertNotNull(resultado);
        assertEquals(resultado, user);
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Retorna exception para um ID inválido")
    void teste02() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(id));
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Verifica se um Email é válido")
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

    @Test
    @DisplayName("Cria um Usuário se ele não existir no banco")
    void teste05() throws LoginException {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(user);

        verify(userRepository).save(userCaptor.capture());
        User resultado = userCaptor.getValue();

        assertNotNull(resultado);
        assertEquals(user.getEmail(), resultado.getEmail());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    @DisplayName("Verifica tentativa de Login com sucesso")
    void teste06() {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("teste@gmail.com");
        dto.setPassword("1234");

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));

        UserResponseDto logado = userService.login(dto);

        assertNotNull(logado);
        assertEquals(dto.getEmail(), logado.getEmail());
        verify(userRepository).findByEmail(dto.getEmail());
    }

    @Test
    @DisplayName("Verifica tentativa de Login com Usuário incorreto/inexistente")
    void teste07() {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("teste@gmail.com");
        dto.setPassword("1234");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.login(dto));
        verify(userRepository).findByEmail(dto.getEmail());
    }

    @Test
    @DisplayName("Verifica tentativa de Login com Senha incorreta")
    void teste08() {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("teste@gmail.com");
        dto.setPassword("1234");

        User naoLogado = new User();
        naoLogado.setEmail(dto.getEmail());
        naoLogado.setPassword("4321");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(naoLogado));

        assertThrows(LoginNotSuccessfulException.class, () -> userService.login(dto));
    }

    @Test
    @DisplayName("Verifica se os parametros para atualizar um Usuário estão corretos")
    void teste09() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.updateUser(id, userRequestDto);

        verify(userRepository).save(userCaptor.capture());
        User resultado = userCaptor.getValue();

        assertNotNull(resultado);
        assertEquals(user.getEmail(), resultado.getEmail());
    }
}