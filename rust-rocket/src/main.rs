#[macro_use]
extern crate rocket;

use rocket::fairing::{Fairing, Info, Kind};
use rocket::http::Header;
use rocket::serde::json::Json;
use rocket::serde::{Deserialize, Serialize};
use rocket::{Request, Response};

#[derive(Serialize, Deserialize)]
#[serde(crate = "rocket::serde")]
struct Employee {
    id: i32,
    firstname: String,
    lastname: String,
}

fn fibonacci(n: u32) -> u32 {
    if n <= 1 {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

#[get("/healthcheck")]
fn healthcheck() -> Json<String> {
    Json(String::from("Ok"))
}

#[get("/employees")]
fn get_employees() -> Json<Vec<Employee>> {
    let mut employees = Vec::new();

    employees.push(Employee {
        id: 1,
        firstname: String::from("John"),
        lastname: String::from("Doe"),
    });
    employees.push(Employee {
        id: 2,
        firstname: String::from("Peter"),
        lastname: String::from("Parker"),
    });
    employees.push(Employee {
        id: 3,
        firstname: String::from("Tony"),
        lastname: String::from("Stark"),
    });

    Json(employees)
}

#[post("/employees")]
fn create_employee() -> Json<u32> {
    let n = 40;
    let fib = fibonacci(n);
    Json(fib)
}

#[launch]
fn rocket() -> _ {
    #[cfg(not(debug_assertions))]
    let figment = rocket::Config::figment();
    #[cfg(not(debug_assertions))]
    let port = figment.extract_inner::<u16>("port").unwrap();
    #[cfg(not(debug_assertions))]
    let address = figment
        .extract_inner::<std::net::IpAddr>("address")
        .unwrap();
    #[cfg(not(debug_assertions))]
    println!(
        "Rocket Server has launched from http://{}:{}",
        address, port
    );

    rocket::build()
        .attach(CORS)
        .mount("/", routes![healthcheck, get_employees, create_employee])
}

pub struct CORS;

#[rocket::async_trait]
impl Fairing for CORS {
    fn info(&self) -> Info {
        Info {
            name: "Add CORS headers to responses",
            kind: Kind::Response,
        }
    }

    async fn on_response<'r>(&self, _req: &'r Request<'_>, response: &mut Response<'r>) {
        response.set_header(Header::new("Access-Control-Allow-Origin", "*"));
        response.set_header(Header::new(
            "Access-Control-Allow-Methods",
            "POST, GET, OPTIONS",
        ));
        response.set_header(Header::new("Access-Control-Allow-Headers", "*"));
    }
}
