package com.example.smartcontactmanager.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.smartcontactmanager.dao.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ToastService toastService;

    String user=null;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        System.out.println("Inside");

        model.addAttribute("credentials", new User());
        // return "login";
        return "check";
    }

    @GetMapping("/loginuser")
    public String loginuser(Model model, HttpSession session){
        model.addAttribute("credentials", new User());
        Boolean val=true;
        model.addAttribute("val", val);
        user="yes";
        return "login";
    }

    @GetMapping("/loginadmin")
    public String loginadmin(Model model, HttpSession session){
        model.addAttribute("credentials", new User());
        Boolean val=false;
        model.addAttribute("val",val);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes){

        System.out.println(credentials);
        String username = credentials.getEmail();
        
        String password = credentials.getPassword();
        System.out.println(password);
        String message = null;

        try {
            if (authenticationService.checkCredentials(username, password)) {

                String role=this.userRepository.findRole(username);
                if(user==null && !role.equals("ROLE_ADMIN")){
                    message="You're not a admin";
                    model.addAttribute("message", message);

                    return "login";
                }

                authenticationService.loginUser(session, username);
                System.out.println("Successfully logged in");
                // toastService.redirectWithSuccessToast(redirectAttributes, "Successfully logged in.");
                model.addAttribute("credentials", credentials);
                return "redirect:/";
            }
            message = "Incorrect password";
        } catch (Exception e) {
            message = "No user with this username found.";
            System.out.println("Incorrect");
        }

        model.addAttribute("credentials", credentials);
        model.addAttribute("message", message);
        // toastService.displayErrorToast(model, errorMessage);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        user=null;
        return "redirect:/";
    }
}

