package com.example.smartcontactmanager.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartcontactmanager.dao.UserRepository;
import com.example.smartcontactmanager.entities.User;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository user;
    private String SESSION_AUTH_KEY = "AUTH_USER";

    public Boolean checkCredentials(String username, String password) {
        //username="great@123";
        User users=null;
        try{
        users = user.getUserByUserName(username);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
        System.out.println(users.getPassword());
        return users.getPassword().equals(password);
    }

    public void loginUser(HttpSession session, String username) {
        session.setAttribute(SESSION_AUTH_KEY, username);
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(SESSION_AUTH_KEY);
    }

    public String getCurrentUser(HttpSession session) {
        try {
            return session.getAttribute(SESSION_AUTH_KEY).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }
}

