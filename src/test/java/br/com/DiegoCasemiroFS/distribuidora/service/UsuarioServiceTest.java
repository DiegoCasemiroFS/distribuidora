package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario foundUsuario = usuarioService.findById(1L);

        assertNotNull(foundUsuario);
        assertEquals(1L, foundUsuario.getId());
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

        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        List<Usuario> usuarios = List.of(usuario1, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> foundUsuarios = usuarioService.findAll();

        assertNotNull(foundUsuarios);
        assertEquals(2, foundUsuarios.size());
        assertEquals(1L, foundUsuarios.get(0).getId());
        assertEquals(2L, foundUsuarios.get(1).getId());
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