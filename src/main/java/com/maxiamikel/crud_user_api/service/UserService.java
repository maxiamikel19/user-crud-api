package com.maxiamikel.crud_user_api.service;

import java.util.List;

import com.maxiamikel.crud_user_api.dto.UserCreateDTO;
import com.maxiamikel.crud_user_api.dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserCreateDTO request);

    List<UserDTO> getAllUser();

    UserDTO getUserById(Long userId);

    UserDTO updateUser(UserCreateDTO request, Long userId);

    void deleteUser(Long userId);
}
