var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();

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

app.MapControllers();

app.Run();
