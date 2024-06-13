package com.devLearning.Webservices.curd_app_ws.service.impl;

import com.devLearning.Webservices.curd_app_ws.io.entity.UserEntity;
import com.devLearning.Webservices.curd_app_ws.io.repository.UserRepository;
import com.devLearning.Webservices.curd_app_ws.shared.Utils;
import com.devLearning.Webservices.curd_app_ws.service.UserService;
import com.devLearning.Webservices.curd_app_ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {



    UserRepository userRepository;


    @Autowired
    Utils utils;




    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("testEncryptedPassword");
        userEntity.setEmailVerificationToken("testEmailVerificationToken");
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue  = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);


        return returnValue;
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByEmail(email);
//
//        if (userEntity == null)
//            throw new UsernameNotFoundException(email);
//
//
//        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
//    }



}
