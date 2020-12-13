package com.example.SpringSecurityHW8.controllers.utils;

import com.example.SpringSecurityHW8.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseBody
    public String error(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    public String error(
//            ResourceNotFoundException ex,
//            Model model
//    ) {
//        System.out.println(ex.getMessage());
//        model.addAttribute("error", ex.getMessage());
//        return "505";
//    }

}
