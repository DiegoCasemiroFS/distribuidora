package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.LoginNotSuccessfulException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User createUser(User user) throws LoginException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()){
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setPhone(user.getPhone());
            newUser.setAddress(user.getAddress());
            newUser.setUserType(user.getUserType());
            newUser.setAdmin(user.isAdmin());

            return userRepository.save(newUser);
        }
        throw new LoginException("Este Usuário já existe");
    }

    public User login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (requestDto.getPassword().equals(user.getPassword())){
            return user;
        }
        throw new LoginNotSuccessfulException();
    }

    public User updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setPhone(userRequestDto.getPhone());
                    user.setAddress(userRequestDto.getAddress());
                    return userRepository.save(user);
                }).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}