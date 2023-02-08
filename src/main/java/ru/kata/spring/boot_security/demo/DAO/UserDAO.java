package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    Optional<User> getUserByUsername(String username);
}
