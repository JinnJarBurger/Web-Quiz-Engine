package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.Summary;
import net.therap.webQuizEngine.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Repository
public class SummaryRepository {

    @PersistenceContext
    private EntityManager em;

    public Summary findByUserAndQuiz(User user, Quiz quiz) {
        List<Summary> summaries = em.createNamedQuery("Summary.findByUserAndQuiz", Summary.class)
                .setParameter("user", user)
                .setParameter("quiz", quiz)
                .getResultList();

        return summaries.isEmpty() ? null : summaries.get(0);
    }

    @Transactional
    public Summary saveOrUpdate(Summary summary) {
        if (summary.isNew()) {
            em.persist(summary);
        } else {
            summary = em.merge(summary);
        }
        em.flush();

        return summary;
    }

    @Transactional
    public void delete(Summary summary) {
        em.remove(em.getReference(Summary.class, summary.getId()));
    }
}
