// package com.example.smartcontactmanager.config;
// import java.util.*;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.DefaultSecurityFilterChain;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.web.util.matcher.RequestMatcher;

// import jakarta.servlet.Filter;

// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// @Configuration
// @EnableWebSecurity
// public class myconfig{

//     @Bean
//     public UserDetailsService getUserDetailsService() {
//         // Return your custom UserDetailsService implementation here.
//         return new UserDetailsServiceImpl();
//     }

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public DaoAuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        
//         daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
//         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        
//         return daoAuthenticationProvider;
//     }

    
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth.authenticationProvider(authenticationProvider());
//     }

//     // protected void configure(HttpSecurity http) throws Exception {
//         // http
//         //     .authorizeRequests()
//         //         .antMatchers("/admin/**").hasRole("ADMIN")
//         //         .antMatchers("/user/**").hasRole("USER")
//         //         .antMatchers("/**").permitAll()
//         //     .and()
//         //     .formLogin()
//         //     .loginPage("/signin")
//         //     .and()
//         //     .csrf().disable();

//     // }

//     // @Bean
//     // public SecurityFilterChain configuSecurityFilterChain() {
//     //     DefaultSecurityFilterChain chain = new DefaultSecurityFilterChain(
//     //         new AntPathRequestMatcher("/admin/**"),
//     //         Arrays.asList(
//     //             // Add your filters for the "/admin/**" pattern here
//     //         )
//     //     );
//     //     return chain;
//     // }

//     // @Bean
//     // public SecurityFilterChain configSecurityFilterChain() {
//     //     DefaultSecurityFilterChain chain = new DefaultSecurityFilterChain(
//     //         new AntPathRequestMatcher("/user/**"),
//     //         Arrays.asList(
//     //             // Add your filters for the "/user/**" pattern here
//     //         )
//     //     );
//     //     return chain;
//     // }


//     // @Bean
//     // public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//     //     http
//     //         .authorizeRequests(authorizeRequests ->
//     //             authorizeRequests
//     //                 .antMatchers("/user/**").hasRole("USER")  // Protect URLs starting with "/user/"
//     //                 .antMatchers("/admin/**").hasRole("ADMIN")  // Protect URLs starting with "/admin/"
//     //                 .anyRequest().authenticated()
//     //         )
//     //         .formLogin(withDefaults()); // Use default form login

//     //     return http.build();
//     // }
// //     // Other security configuration methods go here
// }
