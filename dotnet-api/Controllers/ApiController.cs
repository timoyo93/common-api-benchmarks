using Microsoft.AspNetCore.Mvc;

namespace dotnet_api.Controllers;

[ApiController]
[Route("/")]
public class ApiController : ControllerBase
{
    private int Fibonacci(int n)
    {
        if (n <= 1)
        {
            return n;
        }

        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    [HttpGet("healthcheck")]
    [Produces("application/json")]
    public ActionResult<string> Healthcheck()
    {
        return Ok("Ok");
    }

    [HttpGet("employees")]
    [Produces("application/json")]
    public ActionResult<List<Employee>> GetEmployees()
    {
        var employees = new List<Employee>
        {
            new() { Id = 1, Firstname = "John", Lastname = "Doe" },
            new() { Id = 2, Firstname = "Peter", Lastname = "Parker" },
            new() { Id = 3, Firstname = "Tony", Lastname = "Stark" }
        };
        return Ok(employees);
    }

    [HttpPost("employees")]
    [Produces("application/json")]
    public ActionResult<int> CreateEmployee()
    {
        var n = 40;
        var fib = Fibonacci(n);
        return Ok(fib);
    }
}