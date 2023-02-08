package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role addRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class).getResultList());
    }

    @Override
    public Optional<Role> findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }
}
