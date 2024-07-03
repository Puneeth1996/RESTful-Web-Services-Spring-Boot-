package com.vehicle.details.service.impl;

import com.vehicle.details.entity.User;
import com.vehicle.details.entity.UserPrincipal;
import com.vehicle.details.repository.UserRepo;
import com.vehicle.details.security.JwtService;
import com.vehicle.details.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



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
    public String getAuthToken(User user) {

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String userId = "New user id";
        if(authentication.isAuthenticated()) {
            return "Auth token: " + jwtService.generateToken(user.getUsername());
        }else {
            throw new RuntimeException("User Provided Email or Password could not be Authenticated.");
        }

    }

    @Override
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Error 404");
        } else {
            return new UserPrincipal(user);
        }
    }
}
