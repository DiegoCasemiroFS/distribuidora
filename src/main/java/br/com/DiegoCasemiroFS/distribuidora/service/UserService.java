package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.LoginResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import br.com.DiegoCasemiroFS.distribuidora.security.JwtService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users findById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Users findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<Users> findAll(){
        return userRepository.findAll();
    }

    public Users createUser(Users users) throws LoginException {
        Optional<Users> user = userRepository.findByEmail(users.getEmail());

        if (user.isEmpty()){
            Users newUser = new Users();
            newUser.setName(users.getName());
            newUser.setEmail(users.getEmail());
            newUser.setPassword(users.getPassword());
            newUser.setPhone(users.getPhone());
            newUser.setUserType(users.getUserType());
            newUser.setAddress(users.getAddress());
            newUser.setAdmin(users.isAdmin());

            String token = jwtService.geraToken(newUser);

            return userRepository.save(newUser);
        }
        throw new LoginException("User already exists");
    }

    public LoginResponseDto login(LoginRequestDto requestDto) throws LoginException {
        Users users = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(requestDto.getPassword(), users.getPassword())) {
            String token = jwtService.geraToken(users);
            return new LoginResponseDto(users.getName(), token, users.isAdmin());
        }
        throw new LoginException("Invalid email or password");
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