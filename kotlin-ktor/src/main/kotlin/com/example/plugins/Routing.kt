package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/healthcheck") {
            call.respondText("\"Ok\"", ContentType.Application.Json, HttpStatusCode.OK)
        }
        route("/employees") {
            get {
                val employees = listOf(
                    Employee(1, "John", "Doe"),
                    Employee(2, "Peter", "Parker"),
                    Employee(3, "Tony", "Stark"))
                call.respond(HttpStatusCode.OK, employees)
            }
            post {
                val n = 40
                val fib = fibonacci(n)
                call.respond(HttpStatusCode.OK, fib)
            }
        }
    }
}

fun fibonacci(n: Int): Int {
    if (n <= 1) {
        return n
    }
    return fibonacci(n - 1) + fibonacci(n - 2)
}

@Serializable
data class Employee(
    val id: Int,
    val firstname: String,
    val lastname: String,
)
