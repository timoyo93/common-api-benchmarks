package com.example.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@CrossOrigin(origins = ["*"])
class KotlinSpringApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringApplication>(*args)
}

@RestController
class ExampleResource {
    @GetMapping(value = ["/healthcheck"], produces = ["application/json"])
    fun doHealthcheck():ResponseEntity<String> = ResponseEntity("\"Ok\"", HttpStatus.OK)

    @GetMapping("/employees")
    fun getEmployees(): ResponseEntity<List<Employee>> = ResponseEntity(listOf(
        Employee(1, "John", "Doe"),
        Employee(2, "Peter", "Parker"),
        Employee(3, "Tony", "Stark")),
        HttpStatus.OK
    )

    @PostMapping("/employees")
    fun postEmployees(): ResponseEntity<Int> {
        Thread.sleep(300)
        return ResponseEntity(42, HttpStatus.OK)
    }
}

data class Employee(val id: Int, val firstname: String, val lastname: String)
