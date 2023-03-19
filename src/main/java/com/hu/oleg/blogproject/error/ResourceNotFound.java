package com.hu.oleg.blogproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;

//    public ResourceNotFound(String resourceName,String fieldName,String fieldValue){
//        super("%s not found with %s: %s".formatted(resourceName,fieldName,fieldValue));
//
//        this.fieldName = fieldName;
//        this.resourceName = resourceName;
//        this.fieldValue = fieldValue;
//    }
    public ResourceNotFound(String resourceName,String fieldName,Object fieldValue){
        super("%s not found with %s: %s".formatted(resourceName,fieldName,fieldValue));

        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue.toString();
    }
}
