package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/findUser")
    public User findUserById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers(){
        return usuarioService.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return usuarioService.createUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return usuarioService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@PathVariable Long id){
        usuarioService.deleteUser(id);
    }
}
