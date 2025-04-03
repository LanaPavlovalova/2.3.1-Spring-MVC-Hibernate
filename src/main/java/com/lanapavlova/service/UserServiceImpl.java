package com.lanapavlova.service;

import com.lanapavlova.dao.UserDao;
import com.lanapavlova.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void createUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userDao.save(user);
    }

    @Override
    public void updateUser(Long id, String name, int age) {
        User user = userDao.findById(id);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            userDao.update(user);
        }
    }

    @Override
    public void delete(Long id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }
}