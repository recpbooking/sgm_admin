package com.recp.recpbooking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public ModelAndView dashboard() {
        return new ModelAndView("admin/calendar");
    }

}
