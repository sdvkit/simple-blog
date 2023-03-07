package com.sdv.kit.blogserver.dto;

import java.io.Serializable;

public record UserRegistrationDto(String username,
                                  String email,
                                  String password) implements Serializable {
}