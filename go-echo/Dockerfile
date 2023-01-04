FROM amd64/golang:1.19.4-bullseye AS builder
WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download && go mod verify
COPY . .
RUN go build -o go-echo server.go

FROM --platform=linux/amd64 debian:11.6-slim
COPY --from=builder /app/go-echo /go-echo
EXPOSE 8020
CMD ["/go-echo"]
