package com.web.proj.WebService.service;

import com.web.proj.WebService.shared.dto.UserDto;
import com.web.proj.WebService.ui.modal.request.UserLoginRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    String getAuthToken(UserLoginRequestModel user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
}
