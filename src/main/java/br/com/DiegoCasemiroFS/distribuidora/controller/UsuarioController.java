package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public User findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
