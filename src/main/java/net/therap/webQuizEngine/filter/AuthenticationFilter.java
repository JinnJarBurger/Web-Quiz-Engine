package net.therap.webQuizEngine.filter;

import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static net.therap.webQuizEngine.controller.UserController.USER_CMD;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
