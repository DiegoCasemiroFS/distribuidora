package br.com.DiegoCasemiroFS.distribuidora.repository;

import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setName("User Name");
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setAddress("123 Main St");
        user.setPhone("555-1234");
        user.setAdmin(false);
        user.setUserType(UserType.FORNECEDOR);
        entityManager.persistAndFlush(user);
    }

    @Test
    void testFindById() {
        Optional<Users> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(user.getName());
    }

    @Test
    void testSaveUser() {
        Users newUser = new Users();
        newUser.setName("New User");
        Users savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("New User");
    }

    @Test
    void testDeleteUser() {
        userRepository.delete(user);
        Optional<Users> deletedUser = userRepository.findById(user.getId());
        assertThat(deletedUser).isNotPresent();
    }
}