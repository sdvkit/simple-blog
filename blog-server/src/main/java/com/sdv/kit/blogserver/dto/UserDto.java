package com.sdv.kit.blogserver.dto;

import java.io.Serializable;

public record UserDto(String username, String email, String avatarUrl) implements Serializable {
}