package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.stereotype.Service;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Service
public class QuestionAccessManager {

    public void checkAccess() {
        if (SessionUtil.getLoggedInUser().isStudent()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }
}
