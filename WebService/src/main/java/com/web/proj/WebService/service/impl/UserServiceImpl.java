package com.web.proj.WebService.service.impl;

import com.web.proj.WebService.io.entity.UserEntity;
import com.web.proj.WebService.io.repository.UserRepository;
import com.web.proj.WebService.security.AuthenticationFilter;
import com.web.proj.WebService.security.JwtService;
import com.web.proj.WebService.service.UserService;
import com.web.proj.WebService.shared.dto.UserDto;
import com.web.proj.WebService.shared.Utils;
import com.web.proj.WebService.ui.modal.request.UserLoginRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Utils utils;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtService jwtService;


    @Override
    public String getAuthToken(UserLoginRequestModel user) {

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String userId = "New user id";
        if(authentication.isAuthenticated()) {
            return "Auth token: " + jwtService.generateToken(user.getEmail()) + "\n\n" + "UserId: " + this.getUser(user.getEmail()).getUserId();
        }else {
            throw new RuntimeException("User Provided Email or Password could not be Authenticated.");
        }

    }

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmailVerificationToken("testEmailVerificationToken");
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);


        return returnValue;
    }


    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

//    // User Creation
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        // InMemoryUserDetailsManager
//        UserDetails admin = User.withUsername("Amiya")
//                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails user = User.withUsername("Ejaz")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }


}
