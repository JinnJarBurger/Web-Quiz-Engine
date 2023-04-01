package net.therap.webQuizEngine.controller;

import jakarta.validation.Valid;
import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.helper.AnswerHelper;
import net.therap.webQuizEngine.model.Answer;
import net.therap.webQuizEngine.service.AnswerAccessManager;
import net.therap.webQuizEngine.service.AnswerService;
import net.therap.webQuizEngine.validator.AnswerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static net.therap.webQuizEngine.controller.AnswerController.ANSWER_CMD;

/**
 * @author adnan
 * @since 7/30/2022
 */
@Controller
@RequestMapping("/answer")
@SessionAttributes(ANSWER_CMD)
public class AnswerController {

    public static final String ANSWER_CMD = "answer";
    private static final String VIEW_PAGE = "answer/answer";

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerHelper answerHelper;

    @Autowired
    private AnswerAccessManager answerAccessManager;

    @Autowired
    private AnswerValidator answerValidator;

    @InitBinder(ANSWER_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(answerValidator);
    }

    @GetMapping
    public String show(@RequestParam long quizId,
                       ModelMap model) {

        answerAccessManager.checkAccess();

        answerHelper.setUpReferenceData(quizId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(ANSWER_CMD) Answer answer,
                          Errors errors,
                          RedirectAttributes ra,
                          SessionStatus sessionStatus) {

        answerAccessManager.checkAccess();

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        answerService.process(answer);
        ra.addAttribute("answerId", answer.getId());
        sessionStatus.setComplete();

        return Url.REDIRECT + Url.SUMMARY;
    }
}
