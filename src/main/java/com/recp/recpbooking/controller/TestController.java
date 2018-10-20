package com.recp.recpbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hi")
    public ResponseEntity<?> sayHi() {
        return ResponseEntity.ok("Hiiii...");
    }

    @RequestMapping("/login")
    public ModelAndView loadLogin(){
        ModelAndView modelAndView=new ModelAndView("index");
        return modelAndView;
    }
}
