package net.therap.webQuizEngine.controller;

import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.utils.CommonUtils;
import net.therap.webQuizEngine.helper.QuizHelper;
import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Category;
import net.therap.webQuizEngine.model.Quiz;
import net.therap.webQuizEngine.service.QuizAccessManager;
import net.therap.webQuizEngine.service.QuizService;
import net.therap.webQuizEngine.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static java.util.Objects.nonNull;
import static net.therap.webQuizEngine.controller.QuizController.QUIZ_CMD;
import static net.therap.webQuizEngine.model.Action.DELETE;
import static net.therap.webQuizEngine.model.Action.VIEW;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
@Controller
@RequestMapping("/quiz")
@SessionAttributes(QUIZ_CMD)
public class QuizController {

    public static final String QUIZ_CMD = "quiz";
    private static final String CATEGORIES_PAGE = "quiz/categories";
    private static final String LIST_PAGE = "quiz/list";
    private static final String FORM_PAGE = "quiz/quiz";
    private static final String VIEW_PAGE = "quiz/viewQuiz";
    private static final String USER_QUIZ_LIST_PAGE = "quiz/userQuizzes";

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizHelper quizHelper;

    @Autowired
    private QuizAccessManager quizAccessManager;

    @InitBinder(QUIZ_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/list")
    public String showALl(@RequestParam(required = false) Category category,
                          ModelMap model) {

        quizAccessManager.checkAccess();

        model.addAttribute("quizzes",
                nonNull(category) ? quizService.findAllByCategory(category) : quizService.findAll());

        return LIST_PAGE;
    }

    @GetMapping("/user/quizzes")
    public String showUserQuizzes(ModelMap model) {
        quizAccessManager.checkUserQuizzesAccess();
        model.addAttribute("quizzes", quizService.findAllByUser(SessionUtil.getLoggedInUser()));
        System.out.println(quizService.findAllByUser(SessionUtil.getLoggedInUser()));
        return USER_QUIZ_LIST_PAGE;
    }

    @GetMapping("/categories")
    public String show(ModelMap model) {
        quizAccessManager.checkAccess();
        model.addAttribute("categories", Category.values());

        return CATEGORIES_PAGE;
    }

    @GetMapping
    public String show(@RequestParam(required = false) Action action,
                       @RequestParam(defaultValue = "0") long quizId,
                       ModelMap model) {

        Quiz quiz = getOrCreate(quizId);
        quizAccessManager.checkAccess(action, quiz);

        quizHelper.setUpReferenceData(action, quiz, model);

        return nonNull(action) && action.equals(VIEW) ? VIEW_PAGE : FORM_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(QUIZ_CMD) Quiz quiz,
                          Errors errors,
                          @RequestParam Action action,
                          RedirectAttributes ra,
                          SessionStatus sessionStatus) {

        quizAccessManager.checkAccess(action, quiz);

        if (errors.hasErrors()) {
            return FORM_PAGE;
        }

        quizService.process(action, quiz);
        CommonUtils.setupReferenceData(action, ra);
        sessionStatus.setComplete();

        return action.equals(DELETE)
                ? Url.REDIRECT + Url.USER_QUIZZES
                : Url.REDIRECT + Url.VIEW_QUIZ + quiz.getId();
    }

    private Quiz getOrCreate(long quizId) {
        return quizId == 0 ? new Quiz() : quizService.findById(quizId);
    }
}
