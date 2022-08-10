package net.therap.webQuizEngine.controller;

import net.therap.webQuizEngine.service.HistoryAccessManager;
import net.therap.webQuizEngine.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mohammadhossain
 * @since 7/28/22
 */
@Controller
@RequestMapping("/history")
public class HistoryController {

    private static final String LIST_PAGE = "history/list";

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryAccessManager historyAccessManager;

    @GetMapping("/list")
    public String showAll(ModelMap model) {
        historyAccessManager.checkAccess();

        model.addAttribute("histories", historyService.findAll());

        return LIST_PAGE;
    }
}
