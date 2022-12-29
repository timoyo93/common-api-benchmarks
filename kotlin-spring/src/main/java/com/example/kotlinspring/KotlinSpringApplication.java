package com.example.kotlinspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KotlinSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotlinSpringApplication.class, args);
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello world!";
    }
}
