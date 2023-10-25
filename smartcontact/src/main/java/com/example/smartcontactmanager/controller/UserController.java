// package com.example.smartcontactmanager.controller;

// import java.security.Principal;
// import java.sql.SQLException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.AutoConfigureOrder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.smartcontactmanager.dao.UserRepository;
// import com.example.smartcontactmanager.entities.User;


// @Controller
// @RequestMapping("/user")
// public class UserController {

//     @Autowired
//     private UserRepository userRepository;

//     @RequestMapping("/index")
//     public String dashboard(Model model, Principal principal){
//         String userName=principal.getName();

//         System.out.println("USERNAME: "+userName);
//         try{
//         User user = userRepository.getUserByUserName(userName);
//         }
//         catch(ClassNotFoundException | SQLException e){
//             e.printStackTrace();
//         }

//         System.out.println("USER: "+user);
//         model.addAttribute("user", user);

//         return "normal/user_dashboard";
//     }
// }
