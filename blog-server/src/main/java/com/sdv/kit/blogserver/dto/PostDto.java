package com.sdv.kit.blogserver.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PostDto(Long id,
                      String title,
                      String description,
                      LocalDateTime createdAt) implements Serializable {}