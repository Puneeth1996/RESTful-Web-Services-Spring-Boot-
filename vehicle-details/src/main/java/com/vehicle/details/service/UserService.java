package com.vehicle.details.service;



import com.vehicle.details.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;




public interface UserService extends UserDetailsService {


    User saveUser(User user);
    String getAuthToken(User user);
}
