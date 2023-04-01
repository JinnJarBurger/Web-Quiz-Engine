package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
