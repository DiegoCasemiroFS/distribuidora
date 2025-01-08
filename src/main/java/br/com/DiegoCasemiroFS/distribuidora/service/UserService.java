package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users findById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Users findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<Users> findAll(){
        return userRepository.findAll();
    }

    public Users createUser(Users users){
        return userRepository.save(users);
    }

    public Users updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setAddress(userRequestDto.getAddress());
                    user.setPhone(userRequestDto.getPhone());
                    return userRepository.save(user);
                }).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
