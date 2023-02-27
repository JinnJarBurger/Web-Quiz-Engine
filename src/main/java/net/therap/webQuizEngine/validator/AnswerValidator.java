package net.therap.webQuizEngine.validator;

import net.therap.webQuizEngine.model.Answer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Service
public class AnswerValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Answer.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Answer answer = (Answer) target;

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            if (answer.getAnswers().get(i).isEmpty()) {
                errors.rejectValue("answers", "error.not.filled.answers");
                return;
            }
        }

//        if (answer.getQuiz().getQuestions().size() != answer.getAnswers().size()) {
//            errors.rejectValue("answers", "error.not.filled.answers");
//        }
    }
}
