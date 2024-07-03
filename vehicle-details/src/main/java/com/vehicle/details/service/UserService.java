package com.vehicle.details.service;



import com.vehicle.details.entity.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;




public interface UserService extends UserDetailsService {


    UserDetail saveUser(UserDetail userDetail);
    String getAuthToken(UserDetail userDetail);
}
