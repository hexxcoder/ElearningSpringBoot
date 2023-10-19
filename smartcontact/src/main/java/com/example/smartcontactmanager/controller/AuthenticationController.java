package com.example.smartcontactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.service.AuthenticationService;
import com.example.smartcontactmanager.service.ToastService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ToastService toastService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        // if (authenticationService.isAuthenticated(session)) {
        //     return "loginuser";
        // }
        System.out.println("Inside");

        model.addAttribute("credentials", new User());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // System.out.println("Wow");
        // if (authenticationService.isAuthenticated(session)) {
        //     return "redirect:/";
        // }
        System.out.println(credentials);
        String username = credentials.getEmail();
        System.out.println(username);
        String password = credentials.getPassword();
        String errorMessage = null;

        try {
            if (authenticationService.checkCredentials(username, password)) {
                authenticationService.loginUser(session, username);
                System.out.println("Successfully logged in");
                // toastService.redirectWithSuccessToast(redirectAttributes, "Successfully logged in.");
                model.addAttribute("credentials", credentials);
                return "index";
            }
            errorMessage = "Incorrect password.";
        } catch (Exception e) {
            errorMessage = "No user with this username found.";
            System.out.println("Incorrect");
        }

        model.addAttribute("credentials", credentials);
        // toastService.displayErrorToast(model, errorMessage);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }
}

