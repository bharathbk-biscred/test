package com.example.SpringApplication.configuration;

import com.example.SpringApplication.entity.UserInfo;
import com.example.SpringApplication.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInforepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     Optional<UserInfo> user=userInforepo.findByName(username);
    return  user.map(UserInfoUserDetails::new).orElseThrow(()-> new RuntimeException("User Not FOund "+username));

    }
}
