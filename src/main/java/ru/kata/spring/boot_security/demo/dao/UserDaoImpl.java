package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}