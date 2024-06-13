package com.devLearning.Webservices.curd_app_ws.service;

import com.devLearning.Webservices.curd_app_ws.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
}
