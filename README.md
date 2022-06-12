# ACME-BANK

Simple application to check accounts balance and transfer money.

## Requirements

- [docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/install/)

## Installation

Run: `docker-compose up`

Congrats the application is running! (if not, please wait 1min and retry)

For the endpoints available, please refer to the `requests.http` file.

<img width="759" alt="Screenshot 2022-06-13 at 00 31 20" src="https://user-images.githubusercontent.com/3785828/173243604-8257e116-17c7-4311-903c-46ef6ad1a267.png">


## Docker Services

| Name      | Image                                                                                                        | Port | Container | Information                                   |
|-----------|--------------------------------------------------------------------------------------------------------------|------|-----------|-----------------------------------------------|
| Acme-Bank | [maven:3.8.4-amazoncorretto-17](https://hub.docker.com/_/maven?tab=tags&page=1&name=3.8.4-amazoncorretto-17) | 8080 | acme-bank | - API: localhost:8080/api/v1/account/12345678 |
