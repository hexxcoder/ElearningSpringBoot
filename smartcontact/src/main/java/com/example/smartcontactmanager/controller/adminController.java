package com.example.smartcontactmanager.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.Course;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.AddCourse;
import com.example.smartcontactmanager.helper.Search;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserRepository userRepository;
    String messages=null,messagef=null;
    @GetMapping("/course")
    public String home(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        
        messages=messagef=null;
        model.addAttribute("sm", messages);
        model.addAttribute("fm", messagef);
        return "newcourse";
    }
    
    @PostMapping("/addcourse")
    public String addcourse(@ModelAttribute("user") User credentials, @ModelAttribute("newcourse") AddCourse newcourse, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        String name=newcourse.getName();
        String desc=newcourse.getDesc();
        Long id=newcourse.getId();
        System.out.println("Name: "+name);
        Course course=new Course(id,name,desc);
        messages=null;messagef=null;
        try{
        this.userRepository.addcourse(course);
        messages="Added successfully";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            messagef="Course did not get added";
        }

        model.addAttribute("sm", messages);
        model.addAttribute("fm", messagef);
        return "redirect:/admin/course";
    }
}
