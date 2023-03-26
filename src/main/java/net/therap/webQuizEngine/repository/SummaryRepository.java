package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.Summary;
import net.therap.webQuizEngine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {

    Optional<Summary> findByUserAndQuiz(User user, Quiz quiz);
}
