package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
