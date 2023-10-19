// package com.example.smartcontactmanager.config;

// import org.springframework.security.core.userdetails.UserDetailsService;
// import com.example.smartcontactmanager.entities.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.example.smartcontactmanager.dao.UserRepository;

// public class UserDetailsServiceImpl implements UserDetailsService{
//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        
//         //fetching user from database;

//         User user= userRepository.getUserByUserName(username);

//         if(user==null){
//             throw new UsernameNotFoundException("Could nt found user");
//         }
        
//         CustomUserDetails customUserDetails=new CustomUserDetails(user);
//         return customUserDetails;
//     }
    
// }
