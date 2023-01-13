using dotnet_minimal_api;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddCors(o =>
{
    o.AddPolicy(name: "AllowAll", p =>
    {
        p.AllowAnyOrigin();
        p.AllowAnyMethod();
    });
});
var app = builder.Build();

app.UseCors("AllowAll");

app.MapPost("/employees", () =>
{
    Thread.Sleep(300);
    return Results.Ok(42);
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


app.Run("http://0.0.0.0:8040");
