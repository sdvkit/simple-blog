package com.sdv.kit.blogserver.dto;

import java.io.Serializable;

public record PostCreationDto(String title,
                              String description,
                              Long userId) implements Serializable {
}