using dotnet_minimal_api;

var builder = WebApplication.CreateBuilder(args);
var app = builder.Build();

static int Fibonacci(int n)
{
    if (n <= 1)
    {
        return n;
    }

    return Fibonacci(n - 1) + Fibonacci(n - 2);
}

app.MapPost("/employees", () =>
{
    var n = 40;
    var fib = Fibonacci(n);

    return Results.Ok(fib);
});

app.MapGet("/employees", () =>
{
    var employees = new List<Employee>()
    {
        new() { Id = 1, Firstname = "John", Lastname = "Doe" },
        new() { Id = 2, Firstname = "Peter", Lastname = "Parker" },
        new() { Id = 3, Firstname = "Tony", Lastname = "Stark" }
    };
    return Results.Ok(employees);
});

app.MapGet("/healthcheck", () => Results.Ok("Ok"));


app.Run("http://localhost:8040");