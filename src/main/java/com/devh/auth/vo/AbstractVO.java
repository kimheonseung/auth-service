package com.devh.auth.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractVO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
