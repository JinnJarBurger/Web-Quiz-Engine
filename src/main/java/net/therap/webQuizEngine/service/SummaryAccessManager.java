package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.stereotype.Service;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Service
public class SummaryAccessManager {

    public void checkAccess() {
        if (SessionUtil.getLoggedInUser().isTeacher()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }
}
