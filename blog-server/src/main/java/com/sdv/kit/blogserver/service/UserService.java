package com.sdv.kit.blogserver.service;

import com.sdv.kit.blogserver.dto.UserDto;
import com.sdv.kit.blogserver.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);

    List<UserDto> findAll();
}
