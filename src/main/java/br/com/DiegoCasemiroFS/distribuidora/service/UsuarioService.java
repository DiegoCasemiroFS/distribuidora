package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public User findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll(){
        return usuarioRepository.findAll();
    }

    public User createUser(User user){
        return usuarioRepository.save(user);
    }

    public User updateUser(Long id, UserRequestDto userRequestDto){
        return usuarioRepository.findById(id)
                .map(user -> {
                    user.setAddress(userRequestDto.getAddress());
                    user.setPhone(userRequestDto.getPhone());
                    return user;
                }).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id){
        usuarioRepository.deleteById(id);
    }
}
