//package com.nnk.springboot.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfigOLD {
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/admin").hasRole("ADMIN");
//            auth.requestMatchers("/user").hasRole("USER");
//            auth.anyRequest().authenticated();
//        }).formLogin(Customizer.withDefaults()).build();
//    }
//
//    @Bean
//    public UserDetailsService users() {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(admin);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
//}
