package main

import (
    "net/http"

    "github.com/labstack/echo/v4"
)

func fibonacci(n int) int {
    if n <= 1 {
        return n
    }
    return fibonacci(n-1) + fibonacci(n-2)
}

type Employee struct {
    ID int `json:"id"`
    Firstname string `json:"firstname"`
    Lastname string `json:"lastname"`
}

func getEmployees(c echo.Context) error {
    employees := []Employee {
        {1, "John", "Doe"},
        {2, "Peter", "Parker"},
        {3, "Tony", "Stark"},
    }

    return c.JSON(http.StatusOK, employees)
}

func createEmployee(c echo.Context) error {
    n := 40
    fib := fibonacci(n)

    return c.JSON(http.StatusOK, fib)
}

func healthcheck(c echo.Context) error {
    return c.JSON(http.StatusOK, "Ok")
}

func main() {
    e := echo.New()
    e.GET("/employees", getEmployees)
    e.POST("/employees", createEmployee)
    e.GET("/healthcheck", healthcheck)
    e.Logger.Fatal(e.Start(":8020"))
}
