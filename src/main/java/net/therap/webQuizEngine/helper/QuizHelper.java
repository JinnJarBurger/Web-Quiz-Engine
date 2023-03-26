package net.therap.webQuizEngine.helper;

import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Category;
import net.therap.webQuizEngine.model.Quiz;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static java.util.Objects.isNull;
import static net.therap.webQuizEngine.controller.QuizController.QUIZ_CMD;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Component
public class QuizHelper {

    public void setUpReferenceData(Action action, Quiz quiz, ModelMap model) {
        model.addAttribute(QUIZ_CMD, quiz);

        if (isNull(action)) {
            model.addAttribute("categories", Category.values());
        }
    }
}
