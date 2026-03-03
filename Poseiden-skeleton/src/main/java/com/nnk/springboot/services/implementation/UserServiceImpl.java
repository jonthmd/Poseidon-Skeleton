package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DeleteAdminException;
import com.nnk.springboot.exceptions.UpdateAdminException;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTO> findAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .toList();
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {

        String password = bCryptPasswordEncoder.encode(userDTO.getPassword());

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("This username already exists.");
        }
        User user = userMapper.userDTOToUser(userDTO);
        user.setPassword(password);
        User saved = userRepository.save(user);

        return userMapper.userToUserDTO(saved);
    }

    @Override
    public UserDTO getUser(Integer id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {

        String password = bCryptPasswordEncoder.encode(userDTO.getPassword());

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        if ("ADMIN".equals(user.getRole()) && !"ADMIN".equals(userDTO.getRole())) {
            int adminCount = userRepository.countByRole("ADMIN");
            if (adminCount <=1) {
                throw new UpdateAdminException("You are not allowed to change the last admin's role to USER.");
            }
        }

        user.setFullName(userDTO.getFullName());
        user.setRole(userDTO.getRole());

        if(!userDTO.getPassword().isBlank()){
            user.setPassword(password);
        }
        user.setPassword(password);

        User updated = userRepository.save(user);

        return userMapper.userToUserDTO(updated);
    }

    @Override
    public void deleteUser(Integer id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        if ("ADMIN".equals(user.getRole())) {
            int adminCount = userRepository.countByRole("ADMIN");
            if (adminCount <=1) {
                throw new DeleteAdminException("You are not allowed to delete the last admin.");
            }
        }

        userRepository.deleteById(id);
    }
}
