package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.UserLoginDto;
import com.sdv.kit.blogserver.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserLoginMapper {

    User toEntity(UserLoginDto userLoginDto);

    UserLoginDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserLoginDto userLoginDto, @MappingTarget User user);
}