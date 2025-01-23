package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Users user;
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setAddress("123 Street");
        user.setPhone("1234567890");

        userRequestDto = new UserRequestDto();
        userRequestDto.setAddress("456 Avenue");
        userRequestDto.setPhone("0987654321");
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Users foundUser = userService.findById(1L);

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Users foundUser = userService.findByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindByEmail_NotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findByEmail("test@example.com"));
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindAll() {
        List<Users> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        List<Users> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertFalse(foundUsers.isEmpty());
        assertEquals(1, foundUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() throws LoginException {
        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = userService.updateUser(user.getId(), userRequestDto);

        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
        assertEquals(userRequestDto.getAddress(), updatedUser.getAddress());
        assertEquals(userRequestDto.getPhone(), updatedUser.getPhone());
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user.getId(), userRequestDto));
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteUser(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }
}