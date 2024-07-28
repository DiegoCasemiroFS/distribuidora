package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public User findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    public List<User> findAllUsers(){
        return userService.findAll();
    }

    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    public User updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(id, userRequestDto);
    }

    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
