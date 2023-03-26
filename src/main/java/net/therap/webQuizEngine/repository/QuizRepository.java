package net.therap.webQuizEngine.repository;

import net.therap.webQuizEngine.model.Category;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @NonNull
    List<Quiz> findAll();

    List<Quiz> findAllByCategory(Category category);

    List<Quiz> findAllByUser(User user);
}
