package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Question;
import net.therap.webQuizEngine.model.Quiz;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Repository
public class QuestionRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Question> findByQuiz(Quiz quiz) {
        return em.createNamedQuery("Question.findAllByQuiz", Question.class)
                .setParameter("quiz", quiz)
                .getResultList();
    }

    public Question findById(long id) {
        return em.find(Question.class, id);
    }

    @Transactional
    public Question saveOrUpdate(Question question) {
        if (question.isNew()) {
            em.persist(question);
        } else {
            question = em.merge(question);
        }
        em.flush();

        return question;
    }

    @Transactional
    public void delete(Question question) {
        em.remove(em.getReference(Question.class, question.getId()));
    }
}
