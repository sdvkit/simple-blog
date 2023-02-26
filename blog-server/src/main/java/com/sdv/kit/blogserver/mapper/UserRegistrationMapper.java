package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserRegistrationMapper {

    User toEntity(UserRegistrationDto userRegistrationDto);

    UserRegistrationDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRegistrationDto userRegistrationDto, @MappingTarget User user);
}