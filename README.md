ESD Application

Overview

The ESD application is a Spring Boot project designed to manage faculty, courses, and student grading. It includes features such as authentication, course management, enrollment management, and student grading. The application uses JWT (JSON Web Tokens) for secure authentication and role-based access.

Project Structure

The project follows a layered architecture:

├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── ESD
│   │               ├── EsdApplication.java
│   │               ├── config
│   │               │   ├── JwtTokenUtil.java
│   │               │   ├── RequestInterceptor.java
│   │               │   ├── SecurityConfig.java
│   │               │   └── WebConfig.java
│   │               ├── controller
│   │               │   ├── AuthController.java
│   │               │   └── FacultyController.java
│   │               ├── exceptions
│   │               │   ├── CourseNotFoundException.java
│   │               │   ├── EnrollmentNotFoundException.java
│   │               │   ├── GlobalExceptionHandler.java
│   │               │   ├── InvalidInputException.java
│   │               │   ├── ResourceNotFoundException.java
│   │               │   ├── UnauthorizedAccessException.java
│   │               │   └── UserNotFoundException.java
│   │               ├── filter
│   │               │   └── JwtFilter
│   │               ├── model
│   │               │   ├── AuthRequest.java
│   │               │   ├── AuthResponse.java
│   │               │   ├── Course.java
│   │               │   ├── Enrollment.java
│   │               │   ├── Faculty.java
│   │               │   ├── GradeRequest.java
│   │               │   ├── Student.java
│   │               │   └── Teaching.java
│   │               ├── repository
│   │               │   ├── CourseRepository.java
│   │               │   ├── EnrollmentRepository.java
│   │               │   ├── FacultyRepository.java
│   │               │   ├── StudentRepository.java
│   │               │   └── TeachingRepository.java
│   │               ├── service
│   │               │   ├── CustomUserDetailsService.java
│   │               │   └── FacultyService.java
│   │               └── util
│   │                   └── JwtUtil.java
│   └── resources
│       ├── application.properties
│       ├── data.sql
│       └── scripts.sql
└── test
└── java
└── com
└── example
└── ESD
└── EsdApplicationTests.java

Key Components

Authentication

Login URL: http://localhost:8081/api/v1/auth/login

Sample Credentials:

{
"username": "sylesh",
"password": "password1"
}

Handles authentication via AuthController and generates JWT tokens.

Controllers

AuthController.java: Manages login and token generation.

FacultyController.java: Handles faculty-specific operations like grading students and viewing courses.

Models

Represent database entities such as Faculty, Course, Student, etc.

Repositories

Interface for database operations using Spring Data JPA.

Services

Business logic is encapsulated in service classes like FacultyService.

Filters and Configurations

JwtFilter.java: Verifies JWT tokens for secure endpoints.

SecurityConfig.java: Configures security, roles, and permissions.

Setup Instructions

Prerequisites

Java 17+

Maven 3.6+

MySQL Database

Configuration

Clone the repository:

git clone <repository-url>

Configure the database in src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/esd
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>
spring.jpa.hibernate.ddl-auto=update

Populate the database with initial data using data.sql.

Build and Run

Build the project using Maven:

mvn clean install

Run the application:

mvn spring-boot:run

Usage

Login

Use the login endpoint to authenticate:

POST http://localhost:8081/api/v1/auth/login

Request Body:

{
"username": "sylesh",
"password": "password1"
}

Response:

{
"token": "<JWT_TOKEN>"
}

Grading Students

Use the token to access authorized endpoints such as grading students:

POST http://localhost:8081/api/v1/faculty/courses/2/grade

Request Body:

{
{ "studentId": 1, "grade": "A" }
}

Header:

Authorization: Bearer <JWT_TOKEN>

Testing

Unit tests are located in the test directory.

Run tests using:

mvn test

Additional Notes

Ensure the database is running before starting the application.

Customize roles and permissions in SecurityConfig if needed.

