package com.authapi.authapi.repositories;

import com.authapi.authapi.databaseEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
