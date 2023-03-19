package com.hu.oleg.blogproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private final String resourceName;

    public BadRequestException(String resourceName) {
        super("%s Must Be Valid".formatted(resourceName));
        this.resourceName = resourceName;
    }

    public BadRequestException(String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }
}
