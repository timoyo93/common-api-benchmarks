package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    routing {
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
                delay(300)
                call.respond(HttpStatusCode.OK, 42)
            }
        }
    }
}

@Serializable
data class Employee(
    val id: Int,
    val firstname: String,
    val lastname: String,
)
