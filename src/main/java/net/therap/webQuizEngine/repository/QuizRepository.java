package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Category;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Repository
public class QuizRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Quiz> findAll() {
        return em.createNamedQuery("Quiz.findAll", Quiz.class).getResultList();
    }

    public List<Quiz> findAllByCategory(Category category) {
        return em.createNamedQuery("Quiz.findAllByCategory", Quiz.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Quiz> findAllByUser(User user) {
        return em.createNamedQuery("Quiz.findAllByUser", Quiz.class)
                .setParameter("user", user)
                .getResultList();
    }

    public Quiz findById(long id) {
        return em.find(Quiz.class, id);
    }

    @Transactional
    public Quiz saveOrUpdate(Quiz quiz) {
        if (quiz.isNew()) {
            em.persist(quiz);
        } else {
            quiz = em.merge(quiz);
        }
        em.flush();

        return quiz;
    }

    @Transactional
    public void delete(Quiz quiz) {
        em.remove(em.getReference(Quiz.class, quiz.getId()));
    }
}
