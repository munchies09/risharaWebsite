package com.csc3402.security.formlogin.controller;

import com.csc3402.security.formlogin.dto.UserDto;
import com.csc3402.security.formlogin.model.User;
import com.csc3402.security.formlogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginForm(){
        return "login";}


    @GetMapping("/registration")
    public String registrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null)
            result.rejectValue("email", null,"User already registered!!!");

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/registration";
        }
        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/logout")
    public String userLogout() { return "redirect:/login?logout";}


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";}
    

}
