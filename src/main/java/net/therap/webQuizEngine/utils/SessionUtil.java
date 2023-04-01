package net.therap.webQuizEngine.utils;

import jakarta.servlet.http.HttpSession;
import net.therap.webQuizEngine.model.User;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

/**
 * @author mohammad.hossain
 * @since 3/31/22
 */
public class SessionUtil {

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public static User getLoggedInUser() {
        return (User) getSession().getAttribute("user");
    }
}
