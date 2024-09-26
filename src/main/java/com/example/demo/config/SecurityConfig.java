package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig    {

    @Autowired
    private SecurtityDatabase securtityService;

    /*@Autowired
    public void globalUserDetail(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securtityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/



    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/user/all").hasAnyRole("USER", "MANAGER")
                        .requestMatchers("/user/{id}").hasAnyRole("CHAD", "MANAGER")


                        .anyRequest().authenticated()

                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
}
