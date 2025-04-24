package com.maxiamikel.crud_user_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxiamikel.crud_user_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
