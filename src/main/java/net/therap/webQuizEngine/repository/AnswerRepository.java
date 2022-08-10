package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Repository
public class AnswerRepository {

    @PersistenceContext
    private EntityManager em;

    public Answer findById(long answerId) {
        return em.find(Answer.class, answerId);
    }

    @Transactional
    public Answer saveOrUpdate(Answer answer) {
        if (answer.isNew()) {
            em.persist(answer);
        } else {
            answer = em.merge(answer);
        }
        em.flush();

        return answer;
    }

    @Transactional
    public void delete(Answer answer) {
        em.remove(em.getReference(Answer.class, answer.getId()));
    }
}
