package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotFoundException;
import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private MessageSourceAccessor msa;

    public Answer findById(long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException(msa.getMessage("error.not.found")));
    }

    public void process(Answer answer) {
        User user = answer.getUser();
        Quiz quiz = answer.getQuiz();
        summaryService.process(user, quiz, answer);
    }

}
