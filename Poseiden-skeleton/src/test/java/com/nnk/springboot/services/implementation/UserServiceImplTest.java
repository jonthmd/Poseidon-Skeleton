package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl classUnderTest;

    @Test
    void findAllUsers() {

        //GIVEN
        User user = new User();
        List<User> users = List.of(user);

        UserDTO userDTO = new UserDTO();
        List<UserDTO> userDTOS = List.of(userDTO);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        //WHEN
        List<UserDTO> result = classUnderTest.findAllUsers();

        //THEN
        verify(userRepository).findAll();
        verify(userMapper).userToUserDTO(user);
        assertThat(result).isEqualTo(userDTOS);
    }

    @Test
    void addUser() {

        //GIVEN
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("Jon1234!");

        when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        when(passwordEncoder.encode("Jon1234!")).thenReturn("Jon1234!");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        //WHEN
        UserDTO result = classUnderTest.addUser(userDTO);

        //THEN
        verify(passwordEncoder).encode("Jon1234!");
        verify(userRepository).save(user);
        verify(userMapper).userToUserDTO(user);
        verify(userMapper).userDTOToUser(userDTO);
        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    void addUserAlreadyExists() {

        //GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("existUser");

        //WHEN
        when(userRepository.existsByUsername("existUser")).thenReturn(true);

        //THEN
        assertThatThrownBy(() -> classUnderTest.addUser(userDTO)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getTrade() {

        //GIVEN
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        //WHEN
        UserDTO result = classUnderTest.getUser(1);

        //THEN
        verify(userRepository).findById(1);
        verify(userMapper).userToUserDTO(user);
        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    void getTradeException() {

        //GIVEN
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getUser(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateUser() {

        //GIVEN
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("Jon1234!");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("Jon1234!")).thenReturn("Jon1234!");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        //WHEN
        UserDTO result = classUnderTest.updateUser(1, userDTO);

        //THEN
        verify(userRepository).findById(1);
        verify(passwordEncoder).encode("Jon1234!");
        verify(userRepository).save(user);
        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    void updateUserException() {

        //GIVEN
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.updateUser(1, new UserDTO())).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateUserAdmin() {

        //GIVEN
        User user = new User();
        user.setRole("ADMIN");
        user.setPassword("Jon1234!");

        UserDTO userDTO = new UserDTO();
        userDTO.setRole("USER");
        userDTO.setPassword("Jon1234!");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.countByRole("ADMIN")).thenReturn(2);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        //WHEN
        UserDTO result = classUnderTest.updateUser(1, userDTO);

        //THEN
        verify(userRepository).findById(1);
        verify(userRepository).countByRole("ADMIN");
        verify(userMapper).userToUserDTO(user);
        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    void updateUserAdminException() {

        //GIVEN
        User user = new User();
        user.setRole("ADMIN");
        user.setPassword("Jon1234!");

        UserDTO userDTO = new UserDTO();
        userDTO.setRole("USER");
        userDTO.setPassword("Jon1234!");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.countByRole("ADMIN")).thenReturn(1);

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.updateUser(1, new UserDTO())).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteUser() {

        //GIVEN
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        //WHEN
        classUnderTest.deleteUser(1);

        //THEN
        verify(userRepository).findById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void deleteUserException() {

        //GIVEN
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteUser(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteUserAdmin() {

        //GIVEN
        User user = new User();
        user.setRole("ADMIN");
        user.setPassword("Jon1234!");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.countByRole("ADMIN")).thenReturn(2);

        //WHEN
        classUnderTest.deleteUser(1);

        //THEN
        verify(userRepository).findById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void deleteUserAdminException() {

        User user = new User();
        user.setRole("ADMIN");
        user.setPassword("Jon1234!");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.countByRole("ADMIN")).thenReturn(1);

        assertThatThrownBy(() -> classUnderTest.deleteUser(1)).isInstanceOf(RuntimeException.class);
    }
}