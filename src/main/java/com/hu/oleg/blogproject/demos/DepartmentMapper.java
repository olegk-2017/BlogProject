package com.hu.oleg.blogproject.demos;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentRequest dto);
    Department toEntity(DepartmentResponse dto);

    DepartmentResponse toResponseDto(Department department);
    DepartmentRequest toRequestDto(Department department);

}
