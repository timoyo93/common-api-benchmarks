FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR /source
COPY . .
RUN dotnet publish -c Release -o /app/publish

FROM debian:11.6-slim
RUN apt-get update && apt-get install -y wget
RUN wget https://packages.microsoft.com/config/debian/11/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
RUN dpkg -i packages-microsoft-prod.deb
RUN rm packages-microsoft-prod.deb
RUN apt-get update && apt-get install -y aspnetcore-runtime-7.0

COPY --from=build /app/publish .
EXPOSE 8030
ENTRYPOINT ["dotnet", "dotnet-api.dll"]
