package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String userSignUp(@ModelAttribute User user, Model model) {
        Integer rawAffected = userService.createUser(user);
        if (rawAffected==1) {
            model.addAttribute("signUpSuccess", true);
            return "login";
        }else{
            model.addAttribute("signUpError", true);
            if(rawAffected==-1){
                String errorMessage = "Username already taken, chose another username";
                model.addAttribute("errorMessage", errorMessage);
            }
            return "signup";
        }
    }

}
