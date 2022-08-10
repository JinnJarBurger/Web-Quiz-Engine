package net.therap.webQuizEngine.controller;

import net.therap.webQuizEngine.constant.Url;
import net.therap.webQuizEngine.utils.CommonUtils;
import net.therap.webQuizEngine.helper.QuestionHelper;
import net.therap.webQuizEngine.model.Action;
import net.therap.webQuizEngine.model.Question;
import net.therap.webQuizEngine.service.QuestionAccessManager;
import net.therap.webQuizEngine.service.QuestionService;
import net.therap.webQuizEngine.validator.QuestionValidator;
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

import static net.therap.webQuizEngine.controller.QuestionController.QUESTION_CMD;

/**
 * @author mohammadhossain
 * @since 7/21/22
 */
@Controller
@RequestMapping("/question")
@SessionAttributes(QUESTION_CMD)
public class QuestionController {

    public static final String QUESTION_CMD = "question";
    private static final String VIEW_PAGE = "question/question";

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionAccessManager questionAccessManager;

    @Autowired
    private QuestionHelper questionHelper;

    @Autowired
    private QuestionValidator questionValidator;

    @InitBinder(QUESTION_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(questionValidator);
    }

    @GetMapping
    public String show(@RequestParam long quizId,
                       @RequestParam(defaultValue = "0") long questionId,
                       ModelMap model) {

        questionAccessManager.checkAccess();

        questionHelper.setupReferenceData(quizId, questionId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(QUESTION_CMD) Question question,
                          Errors errors,
                          @RequestParam Action action,
                          RedirectAttributes ra,
                          SessionStatus sessionStatus) {

        questionAccessManager.checkAccess();

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        questionService.process(question, action);
        CommonUtils.setupReferenceData(action, ra);
        sessionStatus.setComplete();

        return Url.REDIRECT + Url.VIEW_QUIZ + question.getQuiz().getId();
    }
}
