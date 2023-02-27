package net.therap.webQuizEngine.service;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author adnan
 * @since 7/18/2022
 */
@Service
public class UserAccessManager {

    public void checkLoginAccess() {
        if (nonNull(SessionUtil.getLoggedInUser())
                && !SessionUtil.getLoggedInUser().isNew()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }

    public void checkSignupAccess() {
        if (!SessionUtil.getLoggedInUser().isNew()) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }

    public void checkLogoutAccess() {
        if (isNull(SessionUtil.getLoggedInUser())) {
            throw new NotAuthorizedException("Action not authorized");
        }
    }
}
