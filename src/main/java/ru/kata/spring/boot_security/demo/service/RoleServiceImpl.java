package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO  = roleDAO;
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        roleDAO.addRole(role);
        return role;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleDAO.findByName(name);
    }
}
