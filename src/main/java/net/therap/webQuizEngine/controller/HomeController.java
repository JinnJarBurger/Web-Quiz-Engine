package net.therap.webQuizEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author adnan
 * @since 7/16/2022
 */
@Controller
@RequestMapping({"", "/", "/home", "/home.jsp"})
public class HomeController {

    private static final String VIEW_PAGE = "home";

    @GetMapping
    public String show() {
        return VIEW_PAGE;
    }
}
