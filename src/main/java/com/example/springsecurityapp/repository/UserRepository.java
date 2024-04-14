package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser>findByName(String userName);
}
