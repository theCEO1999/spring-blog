package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String rollDice(@RequestParam int number, Model model) {
        Random rand = new Random();
        int actual = rand.nextInt((6 - 1) + 1) + 1;
        model.addAttribute("guess", number);
        model.addAttribute("actual", actual);
        if(number == actual){
            model.addAttribute("equals", "Fuckn Spot on! >:0");
        }else{
            model.addAttribute("equals", "Unlucky, Try again...");
        }
        return "/roll-dice";
    }
}
