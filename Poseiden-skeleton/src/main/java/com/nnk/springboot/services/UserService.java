package com.nnk.springboot.services;

import com.nnk.springboot.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();

    UserDTO addUser(UserDTO userDTO);

    UserDTO getUser(Integer id);

    UserDTO updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);
}
