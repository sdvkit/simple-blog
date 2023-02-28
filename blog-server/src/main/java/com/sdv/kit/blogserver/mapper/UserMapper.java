package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.UserDto;
import com.sdv.kit.blogserver.dto.UserLoginDto;
import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    
    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(UserLoginDto userLoginDto);

    UserDto toDto(User user);

    UserRegistrationDto toRegistrationDto(User user);

    UserLoginDto toLoginDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserLoginDto userLoginDto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRegistrationDto userRegistrationDto, @MappingTarget User user);
}