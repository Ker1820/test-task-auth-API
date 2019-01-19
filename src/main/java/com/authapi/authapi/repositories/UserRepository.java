package com.authapi.authapi.repositories;

import com.authapi.authapi.databaseEntities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
