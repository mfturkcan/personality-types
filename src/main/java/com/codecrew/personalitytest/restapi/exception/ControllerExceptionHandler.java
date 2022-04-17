package com.codecrew.personalitytest.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request,
            RedirectAttributes redirectAttributes){
        var errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
                );
        redirectAttributes.addAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }

    @ExceptionHandler(Exception.class)
    public String globalException(Exception exception,
                                WebRequest request,
                                RedirectAttributes redirectAttributes){
        var errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );

        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }
}
