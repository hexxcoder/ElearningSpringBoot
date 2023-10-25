package com.example.smartcontactmanager.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.KeyValuePair;
import com.example.smartcontactmanager.helper.three;
import com.example.smartcontactmanager.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class subjectController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sub{value}")
    public String sub1(@ModelAttribute User credentials, @PathVariable String value, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        String username="";
        username=authenticationService.getCurrentUser(session);
        // List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<three> l=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
                        l=userRepository.getAssignments(Long.parseLong(value),authenticationService.getCurrentUser(session));
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

        

        // for(Map<String,String)

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", l);
        model.addAttribute("m", mm);
        model.addAttribute("cid", value);

        return "sub";
    }


    @GetMapping("/note")
    public String note(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes){

        String username=authenticationService.getCurrentUser(session);
        

        return "note";
    }

    @PostMapping("/submit/{cid}/{value}")
    public String submit(@ModelAttribute User credentials, @PathVariable String value, @PathVariable String cid, Model model, HttpSession session, RedirectAttributes redirectAttributes){

        Long id=Long.parseLong(value);
        System.out.println("Assignment id:"+id);

        String username=authenticationService.getCurrentUser(session);
        Long score=(long)0;
        
        try{
        userRepository.updateAssStat(username,id,Long.parseLong(cid));
        score =this. userRepository.getScore(Long.parseLong(value));
        credentials=userRepository.getUserByUserName(authenticationService.getCurrentUser(session));

        System.out.println("Here score: "+credentials.getScore()+score);
        this.userRepository.updateScore(authenticationService.getCurrentUser(session),score+credentials.getScore());
        }
        catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }

        

        return "redirect:/sub{cid}";
    }

    @GetMapping("/submitted/{value}")
    public String submitted(@ModelAttribute User credentials, @PathVariable String value, Model model, HttpSession session, RedirectAttributes redirectAttributes){

        String username="";
        username=authenticationService.getCurrentUser(session);
        // List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<three> l=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
                        l=userRepository.getSubmittedAssignments(username, Long.parseLong(value));
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
        model.addAttribute("l", l);
        model.addAttribute("m", mm);
        model.addAttribute("cid", value);
        return "submitted";
    }


    @GetMapping("note{cid}")
    public String note(@ModelAttribute User credentials, @PathVariable String cid, Model model, HttpSession session, RedirectAttributes redirectAttributes){


        String username="";
        username=authenticationService.getCurrentUser(session);
        
        List<Map<String,String>> m=new ArrayList<>();
        List<String> l=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
                        l=userRepository.getNotes(Long.parseLong(cid));
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
        model.addAttribute("l", l);
        model.addAttribute("m", mm);
        model.addAttribute("cid", cid);
        return "note";
    }
}
