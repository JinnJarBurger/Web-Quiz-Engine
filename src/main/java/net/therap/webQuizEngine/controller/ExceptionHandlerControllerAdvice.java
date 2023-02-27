package net.therap.webQuizEngine.controller;

import net.therap.webQuizEngine.exception.NotAuthorizedException;
import net.therap.webQuizEngine.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author adnan
 * @since 7/18/2022
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final String ERROR_PAGE = "error";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("status", HttpStatus.NOT_FOUND.value());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public ModelAndView handleNotAuthorized(Exception exception) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("status", HttpStatus.UNAUTHORIZED.value());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntime() {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST.value());

        return modelAndView;
    }
}
