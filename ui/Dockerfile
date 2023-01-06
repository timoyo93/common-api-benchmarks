FROM node:18-alpine AS base

RUN npm i -g pnpm

FROM base AS build
WORKDIR /app
COPY package.json pnpm-lock.yaml ./
RUN pnpm install
COPY . .
RUN pnpm run build

FROM nginx:1.23.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
ARG RUST_API_URL
ARG GO_API_URL
ARG DOTNET_M_API_URL
ARG DOTNET_API_URL
ARG KTOR_API_URL
ARG SPRING_API_URL
RUN sed -i "s/RUST_API_URL/${RUST_API_URL}/g" /etc/nginx/conf.d/default.conf
RUN sed -i "s/GO_API_URL/${GO_API_URL}/g" /etc/nginx/conf.d/default.conf
RUN sed -i "s/DOTNET_M_API_URL/${DOTNET_M_API_URL}/g" /etc/nginx/conf.d/default.conf
RUN sed -i "s/DOTNET_API_URL/${DOTNET_API_URL}/g" /etc/nginx/conf.d/default.conf
RUN sed -i "s/KTOR_API_URL/${KTOR_API_URL}/g" /etc/nginx/conf.d/default.conf
RUN sed -i "s/SPRING_API_URL/${SPRING_API_URL}/g" /etc/nginx/conf.d/default.conf
