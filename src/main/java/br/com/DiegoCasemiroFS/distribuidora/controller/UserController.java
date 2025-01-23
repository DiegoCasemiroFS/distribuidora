package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser/{id}")
    public Users findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Users> findAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/createUser")
    public Users createUser(@RequestBody Users users) throws LoginException {
        return userService.createUser(users);
    }

    @PutMapping("/updateUser/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
