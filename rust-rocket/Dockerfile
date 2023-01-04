FROM amd64/rust:1.66-bullseye AS builder
WORKDIR /app
COPY . .
RUN cargo build --release

FROM --platform=linux/amd64 debian:11.6-slim
RUN apt-get update && apt-get install -y curl
COPY --from=builder /app/target/release/rust-rocket /rust-rocket
COPY --from=builder /app/Rocket.toml /Rocket.toml
EXPOSE 8010
CMD ["/rust-rocket"]
