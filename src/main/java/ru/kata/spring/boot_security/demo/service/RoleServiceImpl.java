package ru.kata.spring.boot_security.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role addRole(Role role) {
        roleDao.addRole(role);
        return role;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }
}