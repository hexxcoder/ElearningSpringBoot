package com.example.smartcontactmanager.controller;
import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.Course;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.AddCourse;
import com.example.smartcontactmanager.helper.Contact;
import com.example.smartcontactmanager.helper.KeyValuePair;
import com.example.smartcontactmanager.helper.Search;
import com.example.smartcontactmanager.helper.message;
import com.example.smartcontactmanager.helper.three;
import com.example.smartcontactmanager.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public String enrollerror=null;

    // @GetMapping("/test")
    // @ResponseBody
    // public String test(){

    //     User user = new User();

    //     user.setFirstName("Karan Das");
    //     user.setEmail("sourav@gmail.com");

    //     // Contact contact = new Contact();

    //     // user.getContacts().add(contact);

    //     // userRepository.save(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRole(),user.getContact());
    //     try {
    //         userRepository.update(user); // Call the update method
    //     } catch (ClassNotFoundException | SQLException e) {
            
    //         e.printStackTrace();
    //     }
    //     return "Working";
    // }

    @RequestMapping("/")
    public String home(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        String username="";
        username=authenticationService.getCurrentUser(session);
        List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<KeyValuePair> ll=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        String admin=null;
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";
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


        // for(Map<String,String)

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", ll);
        model.addAttribute("m", mm);
        model.addAttribute("admin", admin);
        return "index";
    }

    @PostMapping("/enroll{value}")
    public String enroll1(@ModelAttribute User credentials, @PathVariable String value, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        
        try{
            // user=userRepository.getUserByUserName(authenticationService.getCurrentUser(session));
            this.userRepository.adduserenroll(authenticationService.getCurrentUser(session),Integer.parseInt(value));
            this.userRepository.transact(authenticationService.getCurrentUser(session),value);
        }
        catch(ClassNotFoundException | SQLException e){
            enrollerror="You're are not logged in";
            // model.addAttribute("message", message);
        }

        return "redirect:/course";
    }
    
    @RequestMapping("/about")
    public String about(@ModelAttribute("user") User credentials, Model model, HttpSession session){
   	    String username=authenticationService.getCurrentUser(session);
        try{
        credentials=userRepository.getUserByUserName(username);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        System.out.println(credentials);
        // String username="";
        // if(credentials.getEmail()!=null)
        //     username=credentials.getEmail();
        model.addAttribute("credentials", credentials);
        return "about";
    }

    @PostMapping("/course")
    public String postCourse(@ModelAttribute("user") User credentials, @ModelAttribute("search") Search search, Model model, HttpSession session){
        String s=search.getFind();

        String username="";
        username=authenticationService.getCurrentUser(session);
        List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<KeyValuePair> ll=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();
        String admin=null;
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
            if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";
            l = this.userRepository.getUnenrolledCourse(username,s);
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


        // for(Map<String,String)

        model.addAttribute("credentials", credentials);
        model.addAttribute("l", ll);
        model.addAttribute("m", mm);
        model.addAttribute("admin", admin);

        
        
        return "course";
    }

    @GetMapping("/course")
    public String course(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        
   	    String username="";
        username=authenticationService.getCurrentUser(session);
        List<three> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        
        List<KeyValuePair> mm=new ArrayList<>();    
        String admin=null;
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                // System.out.println(this.userRepository.findRole(username));
                if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";

            }
            l = this.userRepository.getUnenrolledCourses(username,"");
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
        model.addAttribute("message", enrollerror);
        model.addAttribute("admin", admin);
        enrollerror=null;
        return "course";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("credentials",new User());
        return "signup";
    }

    //handler for registering user
    
    @RequestMapping(value= "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User credentials, BindingResult result1, @RequestParam(value = "agreement", defaultValue = "false" )boolean agreement, Model model, HttpSession session) throws Exception{
            
            if(result1.hasErrors()){
                System.out.println("ERROR "+result1.toString());
                model.addAttribute("credentials",credentials);
                return "signup"; 
            }
            
            credentials.setRole("ROLE_USER");
            credentials.setScore((long)0);
            // user.setPassword(passwordEncoder.encode(user.getPassword()));
            credentials.setId();
            System.out.println(credentials.getId());
            System.out.println("Score: "+ credentials.getScore());
            System.out.println("User: "+ credentials);

            // this.userRepository.save(credentials.getId(),credentials.getFirstName(),credentials.getLastName(),credentials.getEmail(),credentials.getPassword(),credentials.getRole(),credentials.getContact());
            try {
            this.userRepository.update(credentials); // Call the update method
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Here");
            e.printStackTrace();
        }
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

    @GetMapping("/score")
    public String score(@ModelAttribute("user") User credentials, Model model, HttpSession session){
        String username="";
        username=authenticationService.getCurrentUser(session);
        List<Map<String,String>> l=new ArrayList<>();
        List<Map<String,String>> m=new ArrayList<>();
        List<KeyValuePair> ll=new ArrayList<>();
        List<KeyValuePair> mm=new ArrayList<>();    
        try{
            if(username!=null) credentials=userRepository.getUserByUserName(username);
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

        System.out.println("Score:"+credentials.getScore());
        model.addAttribute("credentials", credentials);
        model.addAttribute("l", ll);
        model.addAttribute("m", mm);
        model.addAttribute("score", credentials.getScore());
        return "score";
    }

    @GetMapping("/contact")
    public String contact(@ModelAttribute("user") User credentials, Model model, HttpSession session){

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
        return "contact";
    }

    @GetMapping("/teacher")
    public String teacher(@ModelAttribute("user") User credentials, Model model, HttpSession session){

        String username="";
        username=authenticationService.getCurrentUser(session);
        
        List<Map<String,String>> m=new ArrayList<>();
        
        List<KeyValuePair> mm=new ArrayList<>(); 
        String admin=null;   
        try{
            if(username!=null) {
                credentials=userRepository.getUserByUserName(username);
                if(this.userRepository.findRole(username).equals("ROLE_ADMIN")) admin="yes";
            }
            
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
        model.addAttribute("admin", admin);
        return "teacher";
    }

    @PostMapping("unenroll/{cid}")
    public String delete(@ModelAttribute User credentials, @PathVariable String cid, Model model, HttpSession session, RedirectAttributes redirectAttributes){

        String username="";
        username=authenticationService.getCurrentUser(session);
  
        try{
            this.userRepository.deleteenroll(username, Long.parseLong(cid));
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        
        return "redirect:/";
    }

    @PostMapping("/contact")
    public String message(@ModelAttribute("user") User credentials, @ModelAttribute("contact") Contact contact, Model model, HttpSession session) throws ClassNotFoundException, SQLException{
        
        String name=contact.getName();
        String email=contact.getEmail();
        String sub=contact.getSubject();
        String det=contact.getDetail();
        Long id=(long)(Math.random()*10000);
        // System.out.println("Name: "+name);
        Contact con=new Contact(id,name,email,sub,det);
        
        try{
        this.userRepository.addcontact(con);

        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return "redirect:/contact";
    }
}

    
