package com.maxiamikel.crud_user_api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxiamikel.crud_user_api.dto.UserCreateDTO;
import com.maxiamikel.crud_user_api.dto.UserDTO;
import com.maxiamikel.crud_user_api.exception.DuplicationCreateUserEmailException;
import com.maxiamikel.crud_user_api.exception.ObjectIdNotFoundException;
import com.maxiamikel.crud_user_api.model.User;
import com.maxiamikel.crud_user_api.repository.UserRepository;
import com.maxiamikel.crud_user_api.service.UserService;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserCreateDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicationCreateUserEmailException("User email aleready exist");
        }

        User obj = new User();
        obj.setId(null);
        obj.setName(request.getName().trim());
        obj.setEmail(request.getEmail().trim());
        obj.setCreatedAt(LocalDateTime.now());
        obj.setUpdatedAt(null);
        return this.mapToDto(userRepository.save(obj));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> list = users.stream().map(user -> this.mapToDto(user)).toList();
        return list;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User opUser = findUserById(userId);
        return this.mapToDto(opUser);
    }

    @Override
    public UserDTO updateUser(UserCreateDTO request, Long userId) {

        User opUser = findUserById(userId);
        if ((userRepository.findByEmail(request.getEmail()).isPresent())
                && !(opUser.getEmail().equals(request.getEmail()))) {
            throw new DuplicationCreateUserEmailException(
                    "User email aleready exist");
        }

        opUser.setName(request.getName().trim());
        opUser.setEmail(request.getEmail());
        opUser.setUpdatedAt(LocalDateTime.now());
        return this.mapToDto(userRepository.save(opUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User opUser = findUserById(userId);
        userRepository.deleteById(opUser.getId());
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectIdNotFoundException("User ID:" + userId + " does not exists"));
    }

    private UserDTO mapToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
