# Contract Risk Analyser

## Project Overview
Contract Risk Analyser is a Spring Boot backend application developed for managing and analysing contract-related data securely and efficiently.

The project includes JWT authentication, Redis caching, PostgreSQL integration, Docker setup, REST APIs, exception handling, and unit testing.

## Tech Stack
- Java
- Spring Boot
- PostgreSQL
- Redis
- JWT
- Docker

## Features
- JWT Authentication
- REST APIs
- Redis Caching
- PostgreSQL Integration
- Docker Setup

## Project Structure
- controller
- service
- repository
- entity
- config
- exception
- dto

## API Endpoints

### Auth APIs
POST /auth/register
POST /auth/login
POST /auth/refresh

### Contract APIs
POST /contracts/create
GET /contracts/all
GET /contracts/{id}

## PostgreSQL Configuration

Database Name:

contract_db

## Setup Instructions

### Run Backend
mvn spring-boot:run

### Run Tests
mvn test

### Docker
docker-compose up --build

## Developed By

Harshitha A
Java Developer 1
Tool Number: 42