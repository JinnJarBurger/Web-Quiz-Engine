package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author adnan
 * @since 7/18/2022
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public User findById(long id) {
        return em.find(User.class, id);
    }

    public User findByUsername(String username) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmail(String email) {
        List<User> users = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    public User findByPassword(String password) {
        List<User> users = em.createNamedQuery("User.findByPassword", User.class)
                .setParameter("password", password)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    @Transactional
    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }

        em.flush();

        return user;
    }

    @Transactional
    public void delete(User user) {
        em.remove(em.getReference(User.class, user.getId()));
    }
}
