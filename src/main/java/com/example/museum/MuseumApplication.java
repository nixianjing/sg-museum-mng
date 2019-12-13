package com.example.museum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.museum.dao")
@ImportResource("classpath:spring/spring-servlet.xml")
@EnableScheduling
@SpringBootApplication
public class MuseumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuseumApplication.class, args);
    }

}
