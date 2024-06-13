package com.devLearning.Webservices.curd_app_ws.ui.controller;

import com.devLearning.Webservices.curd_app_ws.service.UserService;
import com.devLearning.Webservices.curd_app_ws.shared.dto.UserDto;
import com.devLearning.Webservices.curd_app_ws.ui.model.request.UserDetailsRequestModel;
import com.devLearning.Webservices.curd_app_ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/api/users
public class UserController {

	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "Get user method";
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
	

	@PutMapping
	public String updateUser() {
		return "Update user method";
	}
	

	@DeleteMapping
	public String deleteUser() {
		return "Delete user method";
	}
}
