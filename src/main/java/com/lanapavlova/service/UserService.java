package com.lanapavlova.service;

import com.lanapavlova.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void createUser(String name, int age);
    void updateUser(Long id, String name, int age);
    void delete(Long id);
}