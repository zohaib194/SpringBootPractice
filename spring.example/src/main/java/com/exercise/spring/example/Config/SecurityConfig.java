package com.exercise.spring.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Created two users and configured the application to use in memory authentication.
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("public").password(passwordEncoder().encode("public123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // User needs to be authenticated in order to use any request and need to use http basic.
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
