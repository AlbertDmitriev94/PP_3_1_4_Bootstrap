package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleDAO {

    Role addRole(Role role);
    Set<Role> getAllRoles();
    Optional<Role> findByName(String name);
}
