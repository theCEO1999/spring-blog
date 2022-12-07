package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String landingPage() {

        return "This is the landing page, <br> Type: /register at the end of the url to register <br> Type: /login at the end of the url if you already hae an account <br> Type: /posts at the end of the url to see a list of the current posts for all users <br> Type: /{numberofpost} at the end of the url to see more details of only that post";
    }
}
