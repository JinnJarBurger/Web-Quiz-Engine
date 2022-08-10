package net.therap.webQuizEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mohammadhossain
 * @since 7/19/22
 */
@Controller
@RequestMapping("/success")
public class SuccessController {

    private static final String SUCCESS_PAGE = "success";

    @GetMapping
    public String show() {
        return SUCCESS_PAGE;
    }
}
