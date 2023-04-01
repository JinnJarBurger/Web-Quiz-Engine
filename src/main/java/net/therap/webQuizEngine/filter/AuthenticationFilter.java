package net.therap.webQuizEngine.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.model.User;

import java.io.IOException;

import static java.util.Objects.isNull;
import static net.therap.webQuizEngine.controller.UserController.USER_CMD;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (isNull(req.getSession().getAttribute(USER_CMD))
                || ((User) req.getSession().getAttribute(USER_CMD)).isNew()) {

            res.sendRedirect(Url.LOGIN);
        } else {
            chain.doFilter(request, response);
        }
    }
}
