package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotFoundException;
import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Category;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.repository.QuizRepository;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static net.therap.webQuizEngine.model.Action.*;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private MessageSourceAccessor msa;

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz findById(long id) {
        return Optional.ofNullable(quizRepository.findById(id))
                .orElseThrow(() -> new NotFoundException(msa.getMessage("error.no.such.quiz")));

    }

    public List<Quiz> findAllByCategory(Category category) {
        return quizRepository.findAllByCategory(category);
    }

    public List<Quiz> findAllByUser(User user) {
        return quizRepository.findAllByUser(user);
    }

    public void process(Action action, Quiz quiz) {
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            saveOrUpdate(quiz);
        } else if (action.equals(DELETE)) {
            remove(quiz);
        }
    }

    public Quiz saveOrUpdate(Quiz quiz) {
        if (quiz.isNew()) {
            quiz.setCreatedBy(SessionUtil.getLoggedInUser());
        }

        return quizRepository.save(quiz);
    }

    public void remove(Quiz quiz) {
        quizRepository.delete(quiz);
    }
}
