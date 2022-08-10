package net.therap.webQuizEngine.helper;

import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.service.QuizService;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static net.therap.webQuizEngine.controller.AnswerController.ANSWER_CMD;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Component
public class AnswerHelper {

    @Autowired
    private QuizService quizService;

    public void setUpReferenceData(long quizId, ModelMap model) {
        model.addAttribute(ANSWER_CMD, Answer.builder()
                .wrongAnswers(new ArrayList<>())
                .user(SessionUtil.getLoggedInUser())
                .quiz(quizService.findById(quizId))
                .build());
    }
}
