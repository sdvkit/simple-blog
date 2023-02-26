package com.sdv.kit.blogserver.service;

import com.sdv.kit.blogserver.dto.UserRegistrationDto;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);
}
