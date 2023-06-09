# Estágio 1: Construir a aplicação
FROM node:18-alpine as builder

WORKDIR /app

# Copiar o arquivo package.json e package-lock.json para instalar as dependências
COPY package*.json ./

# Instalar as dependências da aplicação
RUN npm install

# Copiar os arquivos da aplicação
COPY . .

# Construir a aplicação
RUN npm run build

# Estágio 2: Executar a aplicação
FROM node:18-alpine

WORKDIR /app

# Copiar a pasta dist (resultado da construção) do estágio anterior para o estágio atual
COPY --from=builder /app/dist ./dist
COPY --from=builder /app/package*.json ./

# Instalar somente as dependências de produção
RUN npm install --production

# Expor a porta em que a aplicação será executada (caso necessário)
EXPOSE 3000

# Comando para iniciar a aplicação
CMD [ "node", "dist/main" ]