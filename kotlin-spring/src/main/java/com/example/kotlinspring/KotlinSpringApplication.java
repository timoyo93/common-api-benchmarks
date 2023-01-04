package com.example.kotlinspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class KotlinSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotlinSpringApplication.class, args);
    }

     int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    @GetMapping(value = "/healthcheck", produces = "application/json")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>("\"Ok\"", HttpStatus.OK);
    }

    @GetMapping(value = "/employees", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Doe"));
        employees.add(new Employee(2, "Peter", "Parker"));
        employees.add(new Employee(3, "Tony", "Stark"));
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping(value = "/employees", produces = "application/json")
    public ResponseEntity<Integer> createEmployee() {
        int n = 40;
        int fib = Fibonacci(n);
        return new ResponseEntity<>(fib, HttpStatus.OK);
    }

}

