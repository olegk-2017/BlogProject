package com.hu.oleg.blogproject;

import com.hu.oleg.blogproject.config.BlogJWTConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@SpringBootApplication
@EnableConfigurationProperties(BlogJWTConfig.class)
public class BlogProjectApplication  implements CommandLineRunner {

//    private final DepartmentMapper mapper;
    public static void main(String[] args) {
        SpringApplication.run(BlogProjectApplication.class, args);
    }

    @Override
    public void run(String... args)  {
//        var dto = mapper.toRequestDto(Department.builder().build());
//        var dto2 = mapper.toResponseDto(Department.builder().build());
//        var entity = mapper.toEntity(new DepartmentResponse(1L,"a","fgdfg","2"));
    }
}
