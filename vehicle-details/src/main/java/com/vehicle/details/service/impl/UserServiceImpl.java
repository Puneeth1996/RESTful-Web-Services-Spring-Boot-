package com.vehicle.details.service.impl;

import com.vehicle.details.entity.UserDetail;
import com.vehicle.details.repository.UserRepo;
import com.vehicle.details.security.JwtService;
import com.vehicle.details.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;



    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    @Override
    public String getAuthToken(UserDetail userDetail) {

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword()));
        String userId = "New user id";
        if(authentication.isAuthenticated()) {
            return "Auth token: " + jwtService.generateToken(userDetail.getUsername());
        }else {
            throw new RuntimeException("User Provided Email or Password could not be Authenticated.");
        }

    }

    @Override
    public UserDetail saveUser(UserDetail userDetail) {
        userDetail.setPassword(encoder.encode(userDetail.getPassword()));
        return userRepo.save(userDetail);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = userRepo.findByUsername(username);
        if (userDetail == null) {
            throw new UsernameNotFoundException("Error 404");
        } else {
            return new User(userDetail.getUsername(), userDetail.getPassword(), new ArrayList<>());
        }
    }
}
