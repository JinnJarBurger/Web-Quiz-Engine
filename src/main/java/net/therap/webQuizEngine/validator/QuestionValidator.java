package net.therap.webQuizEngine.validator;

import net.therap.webQuizEngine.model.Question;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author adnan
 * @since 7/24/2022
 */
@Service
public class QuestionValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Question.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Question question = (Question) target;

        if (question.getOptions().contains(null)) {
            errors.rejectValue("options", "error.null.question.options");
        } else if (!question.getOptions().contains(question.getAnswer())) {
            errors.rejectValue("answer", "error.invalid.question.answer");
        }
    }
}
