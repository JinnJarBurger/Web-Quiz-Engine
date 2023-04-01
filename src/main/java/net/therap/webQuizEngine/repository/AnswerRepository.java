package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
