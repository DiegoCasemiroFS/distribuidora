package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario createUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUser(Long id, UsuarioRequestDto usuarioRequestDto){
        return usuarioRepository.findById(id)
                .map(user -> {
                    user.setAddress(usuarioRequestDto.getAddress());
                    user.setPhone(usuarioRequestDto.getPhone());
                    return user;
                }).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id){
        usuarioRepository.deleteById(id);
    }
}
