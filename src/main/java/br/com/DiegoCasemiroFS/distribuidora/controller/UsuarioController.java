package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UsuarioRequestDto;
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
    public Usuario findUserById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Usuario> findAllUsers(){
        return usuarioService.findAll();
    }

    @PostMapping("/createUser")
    public Usuario createUser(@RequestBody Usuario usuario){
        return usuarioService.createUser(usuario);
    }

    @PutMapping("/updateUser")
    public Usuario updateUser(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto){
        return usuarioService.updateUser(id, usuarioRequestDto);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@PathVariable Long id){
        usuarioService.deleteUser(id);
    }
}
