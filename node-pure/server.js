import http from "http";
import { Employee } from "./employee.mjs";

const processId = process.pid;
const server = http.createServer((req, res) => {
  if (req.url === "/json" && req.method === "GET") {
    const user1 = new Employee(1, "John", "Doe");
    const user2 = new Employee(2, "Jane", "Doe");
    const user3 = new Employee(3, "John", "Smith");
    const users = [user1, user2, user3];

    res.setHeader("Content-Type", "application/json");
    res.statusCode = 200;
    res.end(JSON.stringify(users));
  } else {
    res.statusCode = 404;
    res.end();
  }
});

server.listen(5000).once("listening", () => {
  console.log(`Server Started in process ${processId}`);
});

// Let's simulate Server

process.on("SIGINT", () => {
  console.log("server ending", Date.now());
  server.close(() => process.exit());
});
