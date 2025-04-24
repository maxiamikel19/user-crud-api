package com.maxiamikel.crud_user_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.crud_user_api.dto.UserCreateDTO;
import com.maxiamikel.crud_user_api.dto.UserDTO;
import com.maxiamikel.crud_user_api.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserCreateDTO request) {
        return new ResponseEntity<UserDTO>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<UserDTO>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return new ResponseEntity<List<UserDTO>>(userService.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserCreateDTO request, @PathVariable Long userId) {

        return new ResponseEntity<UserDTO>(userService.updateUser(request, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User was successfully deleted", HttpStatus.OK);
    }
}
