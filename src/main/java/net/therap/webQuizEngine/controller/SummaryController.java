package net.therap.webQuizEngine.controller;

import net.therap.webQuizEngine.helper.SummaryHelper;
import net.therap.webQuizEngine.service.SummaryAccessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Controller
@RequestMapping("/summary")
public class SummaryController {

    public static final String SUMMARY_CMD = "summary";
    private static final String VIEW_PAGE = "/summary/summary";

    @Autowired
    private SummaryHelper summaryHelper;

    @Autowired
    private SummaryAccessManager summaryAccessManager;

    @GetMapping
    public String show(@RequestParam long answerId,
                       ModelMap model) {

        summaryAccessManager.checkAccess();

        summaryHelper.setupReferenceData(answerId, model);

        return VIEW_PAGE;
    }
}
