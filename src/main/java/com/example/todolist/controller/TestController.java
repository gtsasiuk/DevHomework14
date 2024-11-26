package com.example.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {
    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView result = new ModelAndView();
        result.setViewName("test");
        return result;
    }
}
