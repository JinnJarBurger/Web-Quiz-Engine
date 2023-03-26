package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static net.therap.webQuizEngine.model.Action.DELETE;
import static net.therap.webQuizEngine.model.Action.UPDATE;

/**
 * @author adnan
 * @since 7/20/2022
 */
@Service
public class QuizAccessManager {

    public void checkAccess() {
        if (SessionUtil.getLoggedInUser().isTeacher()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }

    public void checkAccess(Action action, Quiz quiz) {
        User user = SessionUtil.getLoggedInUser();
        if (user.isStudent()) {
            throw new NotAuthorizedException("Action not authorized");
        }
        if (Objects.nonNull(action)
                && (action.equals(UPDATE) || action.equals(DELETE))
                && !quiz.getCreatedBy().equals(user)) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }

    public void checkUserQuizzesAccess() {
        if (SessionUtil.getLoggedInUser().isStudent()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }
}
