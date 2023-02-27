package net.therap.webQuizEngine.helper;

import net.therap.webQuizEngine.model.Role;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.therap.webQuizEngine.controller.UserController.USER_CMD;
import static net.therap.webQuizEngine.model.Role.ADMIN;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
@Component
public class UserHelper {

    private static final int DAYS_PER_YEAR = 365;

    public void setupReferenceData(ModelMap model) {
        model.addAttribute(USER_CMD, new User());
        model.addAttribute("roles",
                Arrays.stream(Role.values())
                        .filter(role -> !role.equals(ADMIN))
                        .collect(Collectors.toList()));
    }

    public void setupReferenceData(User user, HttpSession session, ModelMap model) {
        model.addAttribute(USER_CMD, user);
        session.setAttribute("isLoggedIn", true);
    }

    public void calculateAge(User user) {
        Date now = new Date();
        long diffInMillis = Math.abs(now.getTime() - user.getDateOfBirth().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        user.setAge(days / DAYS_PER_YEAR);
        user.setPassword(StringUtil.applySha256(user.getPassword()));
    }
}
