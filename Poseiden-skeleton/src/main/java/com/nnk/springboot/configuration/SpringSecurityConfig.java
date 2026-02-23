package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Bean of the filter chain.
     *
     * @param http The parameter used to configure the web security.
     * @return The configuration of the filter chain.
     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) {
//
//        return http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**")
//                        .permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .failureUrl("/login?error=true")
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll())
//                .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/admin").hasRole("ADMIN");
            auth.requestMatchers("/user").hasRole("USER");
            auth.anyRequest().authenticated();
        }).formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Bean of the authentication rules.
     *
     * @param http                  The parameter used to configure the web security.
     * @param bCryptPasswordEncoder The password encoder.
     * @return The authentication configuration.
     */
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) {
//
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
//
//        return authenticationManagerBuilder.build();
//    }

    /**
     * Bean of the password encoder.
     *
     * @return A encode.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
