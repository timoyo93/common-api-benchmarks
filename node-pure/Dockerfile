# Estágio 1: Construir a aplicação
FROM node:18-alpine as builder

WORKDIR /app


COPY . .

WORKDIR /app

EXPOSE 5000

# Comando para iniciar a aplicação
CMD [ "npm", "start" ]