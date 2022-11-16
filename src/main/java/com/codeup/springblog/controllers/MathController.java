package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public Integer add(@PathVariable Integer num1, @PathVariable Integer num2){
        return num1 + num2;

    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public Integer subtract(@PathVariable Integer num1, @PathVariable Integer num2){
        return num2 - num1;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public Integer multiply(@PathVariable Integer num1, @PathVariable Integer num2){
        return num1 * num2;
    }

    @GetMapping("/divide/{num1}/from/{num2}")
    @ResponseBody
    public Integer divide(@PathVariable Integer num1, @PathVariable Integer num2){
        return num2 / num1;
    }
}
