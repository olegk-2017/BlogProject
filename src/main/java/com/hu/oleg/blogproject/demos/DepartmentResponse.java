package com.hu.oleg.blogproject.demos;

public record DepartmentResponse(
        Long id,
        String name,
        String description,
        String departmentCode
) {
}
