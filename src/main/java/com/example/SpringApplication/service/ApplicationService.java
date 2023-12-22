package com.example.SpringApplication.service;

import com.example.SpringApplication.entity.UserInfo;
import com.example.SpringApplication.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
@Autowired
    UserInfoRepo userInfoRepo;
@Autowired
    PasswordEncoder passwordEncoder;
    public String home(){
        return "Welcome to home page...";
    }
    public String first(){
        return "Welcome to first page...";
    }
    public String second(){
        return "Welcome to second page...";
    }
    public String add(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
       userInfoRepo.save(userInfo);
        return "User added successfully...";
    }
}
