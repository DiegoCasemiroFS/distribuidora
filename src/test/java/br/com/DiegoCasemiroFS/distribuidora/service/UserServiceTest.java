package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.users.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.dto.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.dto.UserResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.LoginNotSuccessfulException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.infra.security.JwtService;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Mock
    private UserRequestDto  userRequestDto;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Captor
    private ArgumentCaptor<User> userCaptor;


    @Test
    @DisplayName("Deve retornar o usuário quando o ID for válido")
    void shouldReturnUserWhenIdIsValid() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User resultado = userService.findById(id);

        assertNotNull(resultado);
        assertEquals(resultado, user);
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar UserNotFoundException quando o ID for inválido")
    void shouldThrowUserNotFoundWhenIdIsInvalid() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(id));
        verify(userRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar o usuário quando o email for válido")
    void shouldReturnUserWhenEmailIsValid() {
        String email = "teste@email.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User resultado = userService.findByEmail(email);

        assertNotNull(resultado);
        assertEquals(resultado, user);
        verify(userRepository).findByEmail(email);
    }

    @Test
    @DisplayName("Deve retornar UserNotFoundException quando o email não for encontrado")
    void shouldThrowUserNotFoundWhenEmailIsInvalid() {
        String email = "teste@email.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findByEmail(email));
        verify(userRepository).findByEmail(email);
    }

    @Test
    @DisplayName("Deve criar o usuário quando ele não existir no banco")
    void shouldCreateUserWhenNotExists() throws LoginException {

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
    @DisplayName("Deve retornar LoginException quando o usuário já existir")
    void shouldThrowWhenUserAlreadyExists() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(LoginException.class, () -> userService.createUser(user));
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    @DisplayName("Deve retornar UserResponseDto quando credenciais forem válidas")
    void shouldLoginSuccessfullyWhenCredentialsAreValid() {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("teste@gmail.com");
        dto.setPassword("1234");

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.geraToken(user)).thenReturn("token");

        UserResponseDto logado = userService.login(dto);

        assertNotNull(logado);
        assertEquals(dto.getEmail(), logado.getEmail());
        verify(userRepository).findByEmail(dto.getEmail());
    }

    @Test
    @DisplayName("Deve retornar UserNotFoundException quando o email for inexistente")
    void shouldThrowUserNotFoundWhenLoginWithUnknownEmail() {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("teste@gmail.com");
        dto.setPassword("1234");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.login(dto));
        verify(userRepository).findByEmail(dto.getEmail());
    }

    @Test
    @DisplayName("Deve retornar LoginNotSuccessfulException quando a senha for incorreta")
    void shouldThrowLoginNotSuccessfulWhenPasswordIsIncorrect() {
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
    @DisplayName("Deve atualizar o usuário quando os parâmetros forem válidos")
    void shouldUpdateUserWhenParamsAreValid() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.updateUser(id, userRequestDto);

        verify(userRepository).save(userCaptor.capture());
        User resultado = userCaptor.getValue();

        assertNotNull(resultado);
        assertEquals(user.getEmail(), resultado.getEmail());
    }

    @Test
    @DisplayName("Deve retornar UserNotFoundException ao tentar atualizar um usuário inexistente")
    void shouldThrowUserNotFoundWhenUpdatingNonExistingUser() {

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(id, userRequestDto));
        verify(userRepository).findById(id);
    }

}