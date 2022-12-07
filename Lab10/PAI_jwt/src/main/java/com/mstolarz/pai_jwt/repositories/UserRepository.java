package com.mstolarz.pai_jwt.repositories;

import com.mstolarz.pai_jwt.models.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}