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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.Course;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.AddAss;
import com.example.smartcontactmanager.helper.AddCourse;
import com.example.smartcontactmanager.helper.AddNote;
import com.example.smartcontactmanager.helper.DropAss;
import com.example.smartcontactmanager.helper.DropNote;
import com.example.smartcontactmanager.helper.KeyValuePair;
import com.example.smartcontactmanager.helper.Search;
import com.example.smartcontactmanager.helper.three;
import com.example.smartcontactmanager.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    
    String courses=null, coursef=null, asss=null, assf=null, notes=null, notef=null;
    @GetMapping("/course")
    public String home(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        
        System.out.println("Course s: "+courses+" Course f: "+coursef);
        model.addAttribute("sm", courses);
        model.addAttribute("fm", coursef);
        courses=coursef=null;

        return "redirect:/course";
    }
    
    @PostMapping("/addcourse")
    public String addcourse(@ModelAttribute("user") User credentials, @ModelAttribute("newcourse") AddCourse newcourse, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        String name=newcourse.getName();
        String desc=newcourse.getDesc();
        Long id=newcourse.getId();
        Long price=newcourse.getPrice();
        Course course=new Course(id,name,desc,price);
        courses=null;coursef=null;
        try{
        this.userRepository.addcourse(course);
        courses="Added successfully";
        return "redirect:/admin/course";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            coursef="Course did not get added";
            return "redirect:/admin/course";
        }

    }

    @PostMapping("/removecourse")
    public String removecourse(@ModelAttribute("user") User credentials, @ModelAttribute("addcourse") AddCourse addcourse, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        
        Long id=addcourse.getId();

        courses=null;coursef=null;
        try{
        this.userRepository.removecourse(id);
        courses="Removed successfully";
        return "redirect:/admin/course";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            coursef="Course did not get removed";
            return "redirect:/admin/course";
        }

    }

    @GetMapping("/assignment")
    public String addass(@ModelAttribute("user") User credentials, Model model, HttpSession session) throws ClassNotFoundException, SQLException{

        String username="";
        username=authenticationService.getCurrentUser(session);
        List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<KeyValuePair> ll=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                // System.out.println(this.userRepository.findRole(username));
                // if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";

            }
            l = this.userRepository.getUnenrolledCourse(username,"");
            m = this.userRepository.getEnrolledCourse(username);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        for(Map<String,String> map:l){
            for(Map.Entry<String,String> entry:map.entrySet()){
                String s1=entry.getKey();
                String s2=entry.getValue();

                ll.add(new KeyValuePair(s1, s2));
            }
        }

        for(Map<String,String> map:m){
            for(Map.Entry<String,String> entry:map.entrySet()){
                String s1=entry.getKey();
                String s2=entry.getValue();

                mm.add(new KeyValuePair(s1, s2));
            }
        }


        for(KeyValuePair k:mm){
            System.out.println("Key: "+k.getKey()+"value: "+k.getValue());
        }

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", ll);
        model.addAttribute("m", mm);
        
        model.addAttribute("sm", asss);
        model.addAttribute("fm", assf);
        asss=assf=null;
        return "adminass";
    }

    @PostMapping("/newass")
    public String addass(@ModelAttribute("user") User credentials, @ModelAttribute("newass") AddAss newass, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        Long id=newass.getId();
        String name=newass.getName();
        Long cid=newass.getCid();
        Long score=newass.getScore();
        
        // System.out.println("Name: "+name);
        AddAss ass=new AddAss(id,cid,score,name);
        
        try{
        this.userRepository.addassignment(ass);
        asss="Added successfully";
        return "redirect:/admin/assignment";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            assf="Assignment did not get added";
            return "redirect:/admin/assignment";
        }

        
    }

    @PostMapping("/removeass")
    public String removeass(@ModelAttribute("user") User credentials, @ModelAttribute("ass") DropAss ass, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        Long id=ass.getId();

        System.out.println("Assignment"+ id);
        
        asss=null;assf=null;
        try{
        this.userRepository.removeassignment(id);
        asss="Removed successfully";
        return "redirect:/admin/assignment";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            assf="Assignment did not get removed";
            return "redirect:/admin/assignment";
        }

        // return "redirect:/admin/assignment";
    }

    @GetMapping("/note")
    public String note(@ModelAttribute("user") User credentials, Model model, HttpSession session) throws ClassNotFoundException, SQLException{

        String username="";
        username=authenticationService.getCurrentUser(session);
        List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<KeyValuePair> ll=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                // System.out.println(this.userRepository.findRole(username));
                // if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";

            }
            l = this.userRepository.getUnenrolledCourse(username,"");
            m = this.userRepository.getEnrolledCourse(username);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        for(Map<String,String> map:l){
            for(Map.Entry<String,String> entry:map.entrySet()){
                String s1=entry.getKey();
                String s2=entry.getValue();

                ll.add(new KeyValuePair(s1, s2));
            }
        }

        for(Map<String,String> map:m){
            for(Map.Entry<String,String> entry:map.entrySet()){
                String s1=entry.getKey();
                String s2=entry.getValue();

                mm.add(new KeyValuePair(s1, s2));
            }
        }


        for(KeyValuePair k:mm){
            System.out.println("Key: "+k.getKey()+"value: "+k.getValue());
        }

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", ll);
        model.addAttribute("m", mm);
        model.addAttribute("sm", notes);
        model.addAttribute("fm", notef);
        notes=notef=null;

        return "adminnote";
    }

    @PostMapping("/newnote")
    public String addNote(@ModelAttribute("user") User credentials, @ModelAttribute("newnote") AddNote newnote, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        Long id=newnote.getId();
        String name=newnote.getName();
        Long cid=newnote.getCid();
        // Long score=newass.getScore();
        
        // System.out.println("Name: "+name);
        AddNote note=new AddNote(id,cid,name);
        notes=null;notef=null;
        try{
        this.userRepository.addnote(note);
        notes="Added successfully";
        return "redirect:/admin/note";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            notef="Note did not get added";
            return "redirect:/admin/note";
        }

    }

    @PostMapping("/deletenote")
    public String removenote(@ModelAttribute("user") User credentials, @ModelAttribute("dropnote") DropNote dropnote, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        Long id=dropnote.getId();

        // System.out.println("Assignment"+ id);
        
        notes=null;notef=null;
        try{
        this.userRepository.removenote(id);
        notes="Removed successfully";
        return "redirect:/admin/note";
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            notef="Assignment did not get removed";
            return "redirect:/admin/note";
        }

        // return "redirect:/admin/assignment";
    }

@GetMapping("/contact")
   public String admincontat(@ModelAttribute("user") User credentials, Model model, HttpSession session) throws ClassNotFoundException, SQLException{

        String username="";
        username=authenticationService.getCurrentUser(session);
        List<three> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        
        List<KeyValuePair> mm=new ArrayList<>();    
        
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                // System.out.println(this.userRepository.findRole(username));
                // if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";

            }
            l = this.userRepository.getContacts();
            
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", l);

        return "admincontact";
    }

    @PostMapping("/view{id}")
    public String view(@ModelAttribute("user") User credentials, @PathVariable String id, Model model, HttpSession session) throws ClassNotFoundException, SQLException{

        String username="";
        username=authenticationService.getCurrentUser(session);
        
        String s="";
        
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
            }
            s = this.userRepository.getView(Long.parseLong(id));
            
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        System.out.println(s);
        model.addAttribute("credentials", credentials);
        model.addAttribute("s", s);

        return "adminview";
    }

}
