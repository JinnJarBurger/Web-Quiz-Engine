package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.stereotype.Service;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Service
public class AnswerAccessManager {

    public void checkAccess() {
        if (SessionUtil.getLoggedInUser().isTeacher()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }
}
