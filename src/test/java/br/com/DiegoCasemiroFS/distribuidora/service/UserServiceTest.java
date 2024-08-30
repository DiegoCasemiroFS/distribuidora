package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = usuarioService.findById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void findById_UserNotFound(){

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> usuarioService.findById(1L));
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void findAll(){

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        List<User> users = List.of(user1, user2);
        when(usuarioRepository.findAll()).thenReturn(users);
        List<User> foundUsers = usuarioService.findAll();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        assertEquals(1L, foundUsers.get(0).getId());
        assertEquals(2L, foundUsers.get(1).getId());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void crateUser() {

    }

    @Test
    void updateUser() {

    }

    @Test
    void deleteUser() {

    }
}