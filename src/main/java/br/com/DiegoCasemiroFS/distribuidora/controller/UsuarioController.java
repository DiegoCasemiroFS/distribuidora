package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/procuraPorId/{id}")
    public ResponseEntity<Usuario> procuraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.procuraPorId(id));
    }

    @GetMapping("/procuraPorEmail")
    public ResponseEntity<Usuario> procuraPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.procuraPorEmail(email));
    }

    @GetMapping("/listaTodos")
    public ResponseEntity<List<Usuario>> listaTodos() {
        return ResponseEntity.ok(usuarioService.listaTodos());
    }

    @PostMapping("/cadastraUsuario")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) throws LoginException {
        return ResponseEntity.ok(usuarioService.cadastraUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(usuarioService.login(requestDto));
    }

    @PutMapping("/alteraUsuario/{id}")
    public ResponseEntity<Usuario> alteraUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.alteraUsuario(id, usuarioRequestDto));
    }

    @DeleteMapping("/deletaUsuario/{id}")
    public ResponseEntity<Void> deletaUsuario(@PathVariable Long id) {
        usuarioService.deletaUsuario(id);
        return ResponseEntity.noContent().build();
    }
}