package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.UserDto;
import com.sdv.kit.blogserver.dto.UserLoginDto;
import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserDto userDto);
    
    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(UserLoginDto userLoginDto);

    UserDto toDto(User user);

    UserRegistrationDto toRegistrationDto(User user);

    UserLoginDto toLoginDto(User user);
}