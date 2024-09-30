package com.backend_training.app.repositories;


import com.backend_training.app.models.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@ComponentScan(basePackages = "com.backend_training.app")
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}