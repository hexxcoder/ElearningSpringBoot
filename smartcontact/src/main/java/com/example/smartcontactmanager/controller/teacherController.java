package com.example.smartcontactmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.KeyValuePair;
import com.example.smartcontactmanager.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class teacherController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public String home(@ModelAttribute("user") User credentials, Model model, HttpSession session){

        String username="";
        username=authenticationService.getCurrentUser(session);
        
        List<Map<String,String>> m=new ArrayList<>();
        
        List<KeyValuePair> mm=new ArrayList<>();    
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
            
            m = this.userRepository.getEnrolledCourse(username);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        for(Map<String,String> map:m){
            for(Map.Entry<String,String> entry:map.entrySet()){
                String s1=entry.getKey();
                String s2=entry.getValue();

                mm.add(new KeyValuePair(s1, s2));
            }
        }

        
        model.addAttribute("credentials", credentials);
        model.addAttribute("m", mm);
        return "";
    }
}
