package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("user", new Users());
//        return "login";
//    }

//    @RequestMapping("/login_success")
//    public String login(@ModelAttribute("user") Users userForm){
//        if(userService.loginUser(userForm)){
//            System.out.println(userForm.getUsername());
//            return "meow";
//        }
//        else return "loginError";
//
//    }

    @GetMapping("/register")
    public String register(Model model){
        Users userForm = new Users();
        model.addAttribute("user", userForm);
        return "register";
    }

    @RequestMapping("/register_success")
    public String register(@ModelAttribute("user") Users userForm){
        userService.saveUser(userForm);
        return "success";
    }
}
