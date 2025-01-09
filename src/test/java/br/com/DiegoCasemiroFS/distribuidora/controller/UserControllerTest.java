package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private Users user;
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = new Users();
        user.setId(1L);
        user.setName("User Name");

        userRequestDto = new UserRequestDto();
        userRequestDto.setAddress("Updated Address");
        userRequestDto.setPhone("123456789");
    }

    @Test
    void testFindUserById() throws Exception {
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/findUser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("User Name"));

        verify(userService, times(1)).findById(1L);
    }

    @Test
    void testFindAllUsers() throws Exception {
        List<Users> userList = Collections.singletonList(user);
        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/user/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("User Name"));

        verify(userService, times(1)).findAll();
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/user/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"User Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("User Name"));

        verify(userService, times(1)).createUser(any(Users.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(eq(1L), any(UserRequestDto.class))).thenReturn(user);

        mockMvc.perform(put("/user/updateUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Updated Address\",\"phone\":\"123456789\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("User Name"));

        verify(userService, times(1)).updateUser(eq(1L), any(UserRequestDto.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/user/deleteUser/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }
}