package net.therap.webQuizEngine.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.helper.UserHelper;
import net.therap.webQuizEngine.model.Role;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.service.UserAccessManager;
import net.therap.webQuizEngine.service.UserService;
import net.therap.webQuizEngine.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.therap.webQuizEngine.controller.UserController.USER_CMD;
import static net.therap.webQuizEngine.model.Role.ADMIN;

/**
 * @author adnan
 * @since 7/18/2022
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(USER_CMD)
public class UserController {

    public static final String USER_CMD = "user";
    private static final String LOGIN_PAGE = "user/login";
    private static final String SIGNUP_PAGE = "user/signup";

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private UserAccessManager userAccessManager;

    @InitBinder(USER_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(userValidator);
    }

    @GetMapping("/signup")
    public String showSignup(ModelMap model) {
        userAccessManager.checkLoginAccess();

        userHelper.setupReferenceData(model);

        return SIGNUP_PAGE;
    }

    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute(USER_CMD) User user,
                                Errors errors,
                                SessionStatus sessionStatus) {

        userAccessManager.checkSignupAccess();

        if (errors.hasErrors()) {
            return SIGNUP_PAGE;
        }

        userService.saveOrUpdate(user);
        sessionStatus.setComplete();

        return Url.REDIRECT + Url.SUCCESS;
    }

    @GetMapping("/login")
    public String showLogin(ModelMap model) {
        userAccessManager.checkLoginAccess();

        model.addAttribute(USER_CMD, new User());

        return LOGIN_PAGE;
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute(USER_CMD) User user,
                               Errors errors,
                               HttpSession session,
                               ModelMap model) {

        userAccessManager.checkLoginAccess();

        if (errors.hasErrors()) {
            return LOGIN_PAGE;
        }

        User userToSet = userService.findByUsername(user.getUsername());
        userHelper.setupReferenceData(userToSet, session, model);

        return Url.REDIRECT + Url.HOME;
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session, SessionStatus sessionStatus) {
        userAccessManager.checkLogoutAccess();

        sessionStatus.setComplete();
        session.invalidate();

        return Url.REDIRECT + Url.SUCCESS;
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.stream(Role.values())
                .filter(role -> !role.equals(ADMIN))
                .collect(Collectors.toList());
    }
}
