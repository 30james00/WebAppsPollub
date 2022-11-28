package com.mstolarz.pai_security.dao;

import com.mstolarz.pai_security.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
