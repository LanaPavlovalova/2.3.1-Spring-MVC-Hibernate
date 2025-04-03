package com.lanapavlova.dao;

import com.lanapavlova.entity.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void update(User user);
    void delete(User user);
}