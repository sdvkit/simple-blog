package com.sdv.kit.blogserver.dto;

import java.io.Serializable;

public record UserLoginDto(String username, String password) implements Serializable {
}