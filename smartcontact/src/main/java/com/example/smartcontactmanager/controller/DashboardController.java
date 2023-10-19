// package com.example.smartcontactmanager.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;

// import com.example.smartcontactmanager.service.AuthenticationService;
// import com.example.smartcontactmanager.service.ToastService;

// import org.springframework.ui.Model;
// import jakarta.servlet.http.HttpSession;

// @Controller
// public class DashboardController {

//     @Autowired
//     private ToastService toastService;

//     @Autowired
//     private AuthenticationService authenticationService;

//     public void addDefaultAttributes(Model model, HttpSession session) {
//         String currentUser = authenticationService.getCurrentUser(session);
//         if (currentUser != null) {
//             model.addAttribute("username", currentUser);
//             model.addAttribute("userImageUrl", "https://ui-avatars.com/api/?name=" + currentUser);

//             String userRole = userService.getRole(currentUser);
//             model.addAttribute("userRole", userRole);

//             if (userRole.equals("student")) {
//                 model.addAttribute("userProfile", studentService.getStudentByRollNo(currentUser));
//             }

//             if (userRole.equals("faculty")) {
//                 model.addAttribute("userProfile", facultyService.getFacultyByEmail(currentUser));
//             }
//         }
//     }

//     public Boolean isAuthenticated(HttpSession session) {
//         return authenticationService.isAuthenticated(session);
//     }

//     @GetMapping("/dashboard")
//     public String dashboard(Model model, HttpSession session) {
//         if (!isAuthenticated(session)) {
//             return "redirect:/";
//         }

//         addDefaultAttributes(model, session);
//         return "dashboard/index";
//     }
    
// }
