package net.therap.webQuizEngine.helper;

import net.therap.webQuizEngine.model.Question;
import net.therap.webQuizEngine.service.QuestionService;
import net.therap.webQuizEngine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static net.therap.webQuizEngine.controller.QuestionController.QUESTION_CMD;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Component
public class QuestionHelper {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MessageSourceAccessor msa;

    public void setupReferenceData(long quizId, long questionId, ModelMap model) {
        Question question = getOrCreate(questionId);
        question.setQuiz(quizService.findById(quizId));
        model.addAttribute(QUESTION_CMD, question);
    }

    private Question getOrCreate(long questionId) {
        return questionId == 0 ?
                new Question() : questionService.findById(questionId);
    }
}
