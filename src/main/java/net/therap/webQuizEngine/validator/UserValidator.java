package net.therap.webQuizEngine.validator;

import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.User;
import net.therap.webQuizEngine.repository.UserRepository;
import net.therap.webQuizEngine.utils.RequestUtil;
import net.therap.webQuizEngine.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static net.therap.webQuizEngine.model.Action.LOGIN;
import static net.therap.webQuizEngine.model.Action.SIGNUP;

/**
 * @author adnan
 * @since 7/16/2022
 */
@Service
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        if (getAction().equals(SIGNUP)) {
            validateSignup(target, errors);
        } else if (getAction().equals(LOGIN)) {
            validateLogin(target, errors);
        }
    }

    private Action getAction() {
        return Action.valueOf(RequestUtil.getRequest().getParameter("action"));
    }

    private void validateSignup(Object target, Errors errors) {
        User user = (User) target;

        if (Optional.ofNullable(userRepository.findByUsername(user.getUsername())).isPresent()) {
            errors.rejectValue("username", "error.unique.user.name");
        }

        if (isNull(user.getEmail())) {
            errors.rejectValue("email", "error.null.user.email");
        } else if (!user.getEmail().matches(".*@.*\\..*$")) {
            errors.rejectValue("email", "error.invalid.user.email");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "error.unique.user.email");
        }

        if (isNull(user.getDateOfBirth())) {
            errors.rejectValue("dateOfBirth", "error.null.user.date.of.birth");
        }

        if (isNull(user.getRole())) {
            errors.rejectValue("role", "error.null.user.role");
        }
    }

    private void validateLogin(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;
        User persistedUser = userRepository.findByUsername(user.getUsername())
                .orElse(null);

        if (isNull(persistedUser)) {
            errors.rejectValue("username", "error.wrong.credentials");
        } else if (!Objects.equals(persistedUser.getPassword(), StringUtil.applySha256(user.getPassword()))) {
            errors.rejectValue("password", "error.wrong.credentials");
        }
    }
}
