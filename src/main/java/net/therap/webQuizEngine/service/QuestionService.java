package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotFoundException;
import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Question;
import net.therap.webQuizEngine.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static net.therap.webQuizEngine.model.Action.*;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MessageSourceAccessor msa;

    public Question findById(long id) {
        return Optional.ofNullable(questionRepository.findById(id))
                .orElseThrow(() -> new NotFoundException(msa.getMessage("error.no.such.question")));
    }

    public void process(Question question, Action action) {
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            saveOrUpdate(question);
        } else if (action.equals(DELETE)) {
            remove(question);
        }
    }

    public Question saveOrUpdate(Question question) {
        return questionRepository.saveOrUpdate(question);
    }

    public void remove(Question question) {
        questionRepository.delete(question);
    }
}
