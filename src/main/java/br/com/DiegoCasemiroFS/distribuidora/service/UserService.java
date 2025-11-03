package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.users.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.UserRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.UserResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.LoginNotSuccessfulException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.infra.security.JwtService;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


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

    public UserResponseDto createUser(User user) throws LoginException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()){
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPhone(user.getPhone());
            newUser.setAddress(user.getAddress());
            newUser.setUserType(user.getUserType());
            newUser.setAdmin(user.isAdmin());

            userRepository.save(newUser);

            String token = jwtService.geraToken(user);
            return new UserResponseDto(
                    user.getEmail(),
                    token,
                    user.isAdmin());
        }
        throw new LoginException("Este Usuário já existe");
    }

    public UserResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            String token = jwtService.geraToken(user);
            return new UserResponseDto(user.getEmail(), token, user.isAdmin());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}