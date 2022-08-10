package net.therap.webQuizEngine.helper;

import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.model.Summary;
import net.therap.webQuizEngine.service.AnswerService;
import net.therap.webQuizEngine.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static net.therap.webQuizEngine.controller.AnswerController.ANSWER_CMD;
import static net.therap.webQuizEngine.controller.SummaryController.SUMMARY_CMD;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Component
public class SummaryHelper {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SummaryService summaryService;

    public void setupReferenceData(long answerId, ModelMap model) {
        Answer answer = answerService.findById(answerId);
        Summary summary = summaryService.findByUserAndQuiz(answer.getUser(), answer.getQuiz());
        model.addAttribute(ANSWER_CMD, answer);
        model.addAttribute(SUMMARY_CMD, summary);
    }
}
