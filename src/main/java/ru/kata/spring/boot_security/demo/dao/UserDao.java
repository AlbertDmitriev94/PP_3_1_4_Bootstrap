package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    void deleteUser(Long id);
    void updateUser(User user);
}