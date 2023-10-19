package com.example.smartcontactmanager.controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.smartcontactmanager.dao.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.message;
import com.example.smartcontactmanager.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/test")
    @ResponseBody
    public String test(){

        User user = new User();

        user.setFirstName("Karan Das");
        user.setEmail("sourav@gmail.com");

        // Contact contact = new Contact();

        // user.getContacts().add(contact);

        userRepository.save(user);

        return "Working";
    }

    @RequestMapping("/")
    public String home(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        String username=authenticationService.getCurrentUser(session);
        if(username!=null) credentials=userRepository.getUserByUserName(username);
        model.addAttribute("credentials", credentials);

        return "index";
    }
    
    @RequestMapping("/about")
    public String about(@ModelAttribute("user") User credentials, Model model, HttpSession session){
   	    String username=authenticationService.getCurrentUser(session);
        credentials=userRepository.getUserByUserName(username);
        System.out.println(credentials);
        // String username="";
        // if(credentials.getEmail()!=null)
        //     username=credentials.getEmail();
        model.addAttribute("credentials", credentials);
        return "about";
    }


    @RequestMapping("/course")
    public String course(@ModelAttribute("user") User credentials, Model model, HttpSession session){
   	    String username=authenticationService.getCurrentUser(session);
        credentials=userRepository.getUserByUserName(username);
        System.out.println(credentials);
        // String username="";
        // if(credentials.getEmail()!=null)
        //     username=credentials.getEmail();
        model.addAttribute("credentials", credentials);
        return "course";
    }

    @RequestMapping("/teacher")
    public String teacher(@ModelAttribute("user") User credentials, Model model, HttpSession session){
   	    String username=authenticationService.getCurrentUser(session);
        credentials=userRepository.getUserByUserName(username);
        System.out.println(credentials);
        // String username="";
        // if(credentials.getEmail()!=null)
        //     username=credentials.getEmail();
        model.addAttribute("credentials", credentials);
        return "teacher";
    }


    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("credentials",new User());
        return "signup";
    }

    //handler for registering user
    
    @RequestMapping(value= "/do_register", method = RequestMethod.POST)
    // @PostMapping(value = "/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User credentials, BindingResult result1, @RequestParam(value = "agreement", defaultValue = "false" )boolean agreement, Model model, HttpSession session){
        //System.out.println("User"+ user);
        //try {

            // if(!agreement){
            //     System.out.println("You have not agreed the terms and conditions");
            //     throw new Exception("You have not agreed the terms and conditions");
            // }
            
            if(result1.hasErrors()){
                System.out.println("ERROR "+result1.toString());
                model.addAttribute("credentials",credentials);
                return "signup"; 
            }
            
            credentials.setRole("ROLE_USER");
            // user.setPassword(passwordEncoder.encode(user.getPassword()));


            System.out.println("Agreement: "+ agreement);
            System.out.println("User: "+ credentials);

            User result=this.userRepository.save(credentials);
            model.addAttribute("credentials",credentials);
            // model.addAttribute("user", new User());

            //session.setAttribute("message",new message("Successfully registered!!", "alert-success"));

            return "redirect:/";

        // } catch (Exception e) {
        //     // TODO: handle exception
        //     e.printStackTrace();
        //     model.addAttribute("user", user);
        //     session.setAttribute("message", new message("Something went wrong!! " + e.getMessage(), "alert-danger"));

        //     return "signup";
        // }
            // return "signup";
    }

    @GetMapping("/signin")
    public String customLogin(Model model){
        model.addAttribute("title", "Login Page");
        return "signup";
    }
}

    
