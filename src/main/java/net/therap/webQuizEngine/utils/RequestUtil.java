package net.therap.webQuizEngine.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Objects.requireNonNull;

/**
 * @author mohammad.hossain
 * @since 3/11/22
 */
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }
}
