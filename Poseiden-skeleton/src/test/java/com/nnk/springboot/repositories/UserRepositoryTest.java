package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {

        //GIVEN
        User user = new User();
        user.setUsername("jon");
        user.setPassword("jon123");
        user.setFullName("jon");
        user.setRole("ROLE_USER");
        userRepository.save(user);

        //WHEN
        User result = userRepository.findByUsername(user.getUsername());

        //THEN
        assertThat(result).isEqualTo(user);
    }

    @Test
    void existsByUsername() {

        //GIVEN
        User user = new User();
        user.setUsername("jon");
        user.setPassword("jon123");
        user.setFullName("jon");
        user.setRole("ROLE_USER");
        userRepository.save(user);

        //WHEN
        boolean result = userRepository.existsByUsername(user.getUsername());

        //THEN
        assertThat(result).isTrue();
    }

    @Test
    void countByRole() {

        //GIVEN
        User user = new User();
        user.setUsername("jon");
        user.setPassword("Jon1234!");
        user.setFullName("jon");
        user.setRole("ADMIN");
        userRepository.save(user);

        //WHEN
        int result = userRepository.countByRole("ADMIN");

        //THEN
        assertThat(result).isEqualTo(1);
    }
}