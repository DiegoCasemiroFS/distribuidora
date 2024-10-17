package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.UserType;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
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
class UsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Users users;

    @BeforeEach
    void setUp(){
        users = new Users();
        users.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(users));

        Users foundUsers = userService.findById(1L);

        assertNotNull(foundUsers);
        assertEquals(1L, foundUsers.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findById_UserNotFound(){

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findAll(){

        Users users1 = new Users();
        users1.setId(1L);

        Users users2 = new Users();
        users2.setId(2L);

        List<Users> users = List.of(users1, users2);
        when(userRepository.findAll()).thenReturn(users);
        List<Users> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        assertEquals(1L, foundUsers.get(0).getId());
        assertEquals(2L, foundUsers.get(1).getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void crateUser() {
        Users users = new Users();
        users.setId(1L);
        users.setName("Diego");
        users.setEmail("diego@email.com");
        users.setPassword("1234");
        users.setAddress("Pastor Francisco Joaquim Mendonça");
        users.setPhone("21980805734");
        users.setAdmin(true);
        users.setUserType(UserType.CLIENTE);

        when(userRepository.save(users)).thenReturn(users);

        Users result = userService.createUser(users);

        assertEquals(result, users);
        verify(userRepository).save(users);
    }

    @Test
    void updateUserSuccessfully() {

        Long userId = 1L;
        Users user = new Users();

        user.setId(userId);
        user.setAddress("Rua A");

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setAddress("Rua B");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Users updateUser = userService.updateUser(userId, userRequestDto);

        assertEquals(userRequestDto.getAddress(), updateUser.getAddress());
        verify(userRepository).findById(userId);

    }

    @Test
    void updateUserNotSuccessfully() {

        Long userId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setAddress("Rua dos Anjos");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, userRequestDto));
        verify(userRepository).findById(userId);
    }

    @Test
    void deleteUser() {

        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);
        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);

    }
}