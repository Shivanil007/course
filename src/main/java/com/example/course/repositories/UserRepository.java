package com.example.course.repositories;

import com.example.course.datamodels.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
