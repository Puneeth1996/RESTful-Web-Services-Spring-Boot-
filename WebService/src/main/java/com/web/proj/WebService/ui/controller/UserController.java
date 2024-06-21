package com.web.proj.WebService.ui.controller;


import com.web.proj.WebService.security.JwtService;
import com.web.proj.WebService.service.impl.UserServiceImpl;
import com.web.proj.WebService.ui.modal.request.UserDetailsRequestModel;
import com.web.proj.WebService.ui.modal.request.UserLoginRequestModel;
import com.web.proj.WebService.ui.modal.response.UserRest;
import com.web.proj.WebService.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }


    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginRequestModel userLoginRequestModel) {
        return userService.getAuthToken(userLoginRequestModel);
    }

    @PutMapping
    public String updateUser() {
        return "Update user method";
    }


    @DeleteMapping
    public String deleteUser() {
        return "Delete user method";
    }
}
