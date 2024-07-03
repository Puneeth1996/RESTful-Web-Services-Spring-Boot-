package com.vehicle.details.controller;

import com.vehicle.details.entity.UserDetail;
import com.vehicle.details.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class HomeController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser() {
        return "This is Home Method";
    }


    @PostMapping
    public ResponseEntity<String> userRegister(@RequestBody UserDetail userDetail) {
        if (userService.saveUser(userDetail) != null) {
            return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Oops! User not registered", HttpStatus.OK);
        }
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody UserDetail UserDetail) {
        return userService.getAuthToken(UserDetail);
    }

}
