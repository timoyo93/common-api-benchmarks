using Microsoft.AspNetCore.Mvc;

namespace dotnet_api.Controllers;

[ApiController]
[Route("/")]
public class ApiController : ControllerBase
{

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
    public async Task<ActionResult<int>> CreateEmployee()
    {
        await Task.Delay(300);
        return Ok(42);
    }
}
