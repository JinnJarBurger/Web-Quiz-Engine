package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import static net.therap.webQuizEngine.model.Role.TEACHER;

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
