package main

import (
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"net/http"
	"time"
)

type Employee struct {
	ID        int    `json:"id"`
	Firstname string `json:"firstname"`
	Lastname  string `json:"lastname"`
}

func getEmployees(c echo.Context) error {
	employees := []Employee{
		{1, "John", "Doe"},
		{2, "Peter", "Parker"},
		{3, "Tony", "Stark"},
	}

	return c.JSON(http.StatusOK, employees)
}

func createEmployee(c echo.Context) error {
	time.Sleep(300 * time.Millisecond)
	return c.JSON(http.StatusOK, 42)
}

func healthcheck(c echo.Context) error {
	return c.JSON(http.StatusOK, "Ok")
}

func main() {
	e := echo.New()
	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"*"},
	}))
	e.GET("/employees", getEmployees)
	e.POST("/employees", createEmployee)
	e.GET("/healthcheck", healthcheck)
	e.Logger.Fatal(e.Start(":8020"))
}
