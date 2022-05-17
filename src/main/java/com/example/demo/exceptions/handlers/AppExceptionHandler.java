package com.example.demo.exceptions.handlers;

import com.example.demo.exceptions.FileStorageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(FileStorageException.class)
    public ModelAndView fileStorageException(FileStorageException e,
                                             RedirectAttributes redirectAttributes) {
        ModelAndView view = new ModelAndView();
        view.addObject("message", e.getMessage());
        view.setViewName("error");
        return view;
    }
}
