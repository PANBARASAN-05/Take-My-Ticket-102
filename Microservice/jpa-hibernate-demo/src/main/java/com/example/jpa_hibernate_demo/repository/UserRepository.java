package com.example.jpa_hibernate_demo.repository;

import com.example.jpa_hibernate_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
