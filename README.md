# BFHL Assignment

## Overview
This project implements a simple Spring Boot REST API for processing an input payload containing a `data` array. It demonstrates clean architecture with separate controller, service, DTO, exception, and configuration layers.

## Assumptions
- The request contains only one field: `data`, which is a JSON array of strings.
- `user_id`, `email`, and `roll_number` are returned as fixed student metadata values for this assignment because the input schema does not provide them.
- Numeric values are string representations of whole numbers.
- Alphabet-only values are converted to uppercase for the `alphabets` list.
- Values that are not purely numeric or alphabetic are treated as `special_characters`.
- Blank string values and null items are rejected as invalid input.

## API Documentation

### POST /bfhl
Process the input array and return classification information.

Request body:
```json
{
  "data": ["123", "aBc", "!@#", "42"]
}
```

Response body:
```json
{
  "is_success": true,
  "user_id": "student-001",
  "email": "student@example.com",
  "roll_number": "BFHL-001",
  "odd_numbers": ["123"],
  "even_numbers": ["42"],
  "alphabets": ["ABC"],
  "special_characters": ["!@#"],
  "sum": "165",
  "concat_string": "CbA"
}
```

### GET /health
Check whether the application is running.

Response body:
```json
{
  "status": "UP"
}
```

## Sample Requests

### Valid request
```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data":["A","ABCD","DOE","10","11","#@$"]}'
```

### Sample response
```json
{
  "is_success": true,
  "user_id": "student-001",
  "email": "student@example.com",
  "roll_number": "BFHL-001",
  "odd_numbers": ["11"],
  "even_numbers": ["10"],
  "alphabets": ["A","ABCD","DOE"],
  "special_characters": ["#@$"],
  "sum": "21",
  "concat_string": "EoDdCbAa"
}
```

## Error response
If the input is invalid, the API returns `400 Bad Request` with a JSON body containing an error message.

Example:
```json
{
  "timestamp": "2026-06-24T12:00:00",
  "status": 400,
  "message": "Request body validation failed",
  "errors": ["data must not be empty"]
}
```

## Run locally

1. Install Java 17 and Maven.
2. Open a terminal in the project root.
3. Run:
```bash
mvn clean package
mvn spring-boot:run
```
4. Call the endpoint at `http://localhost:8080/bfhl`.

## Deploy to Render

1. Create a new Web Service on Render.
2. Connect the Git repository containing this project.
3. Use the build command:
```bash
mvn clean package
```
4. Use the start command:
```bash
java -jar target/bfhl-assignment-0.0.1-SNAPSHOT.jar
```
5. Set the Render environment variable `PORT` if Render assigns a dynamic port. The application is already configured to use `server.port=${PORT:8080}`.
6. After deployment, verify the endpoints:
   - `POST /bfhl`
   - `GET /health`

## Deploy to Railway

1. Create a new project on Railway.
2. Link the Git repository.
3. Set the build command to:
```bash
mvn clean package
```
4. Set the start command to:
```bash
java -jar target/bfhl-assignment-0.0.1-SNAPSHOT.jar
```
5. Railway will expose the service on the assigned port.
