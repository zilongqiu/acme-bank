# ACME-BANK

Simple application to check accounts balance and transfer money.

## Requirements

- [docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/install/)

## Installation

Run: `docker-compose up`

## Docker Services

| Name      | Image                                                                                                        | Port | Container | Information                                   |
|-----------|--------------------------------------------------------------------------------------------------------------|------|-----------|-----------------------------------------------|
| Acme-Bank | [maven:3.8.4-amazoncorretto-17](https://hub.docker.com/_/maven?tab=tags&page=1&name=3.8.4-amazoncorretto-17) | 8080 | acme-bank | - API: localhost:8080/api/v1/account/12345678 |