package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.History;
import net.therap.webQuizEngine.utils.SessionUtil;
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
public class HistoryRepository {

    @PersistenceContext
    private EntityManager em;

    public List<History> findAllByUser() {
        return em.createNamedQuery("History.findAllByUser", History.class)
                .setParameter("user", SessionUtil.getLoggedInUser())
                .setMaxResults(50)
                .getResultList();
    }

    @Transactional
    public History saveOrUpdate(History history) {
        if (history.isNew()) {
            em.persist(history);
        } else {
            history = em.merge(history);
        }
        em.flush();

        return history;
    }

    @Transactional
    public void delete(History history) {
        em.remove(em.getReference(History.class, history.getId()));
    }
}
