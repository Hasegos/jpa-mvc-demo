package com.example.online_community.controller;

import com.example.online_community.model.User;
import com.example.online_community.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired UserService userService;

    /* 회원 가입 페이지 */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerForm") User user,
            BindingResult bindingResult, // 검증 결과
            Model model){

        // 입력값에 오류가있을시
        if(bindingResult.hasErrors()){
            model.addAttribute("error","정확한 입력값을 넣어주세요");
            return "/register";
        }

        try{
            userService.register(user);
            return "redirect:/login";
        }catch (IllegalArgumentException e){
            model.addAttribute("error",e.getMessage());
            return "/register";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "/register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("loginForm") User from,
            BindingResult bindingResult,
            HttpSession session,
            Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "정확한 입력값을 넣어주세요.");
            return "/login";
        }

        try{
            User user = userService.authenticate(from.getUsername(), from.getPassword());
            session.setAttribute("user", user);
            return "redirect:/courses";
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/courses";
    }
}