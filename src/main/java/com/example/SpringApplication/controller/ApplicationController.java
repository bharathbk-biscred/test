package com.example.SpringApplication.controller;

import com.example.SpringApplication.entity.AuthRequest;
import com.example.SpringApplication.entity.UserInfo;
import com.example.SpringApplication.service.ApplicationService;
import com.example.SpringApplication.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/home")
    public String home() {
        return applicationService.home();
    }

    @GetMapping("/first")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String first() {
        return applicationService.first();
    }

    @GetMapping("/second")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String second() {
        return applicationService.second();
    }

    @PostMapping("/add")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return applicationService.add(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new UsernameNotFoundException("user not found " + authRequest.getName());
        }


    }
}
