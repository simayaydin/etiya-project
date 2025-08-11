# E-Commerce Project

## Overview

E-Commerce Project is a modern and secure e-commerce platform developed with Spring Boot.  
It supports both admin and user roles, offering functionality such as product management, cart operations, JWT-based authentication, and monitoring with Prometheus and Grafana.


---

## Features

- **User Authentication & Authorization:** Registration, login, JWT-based security, password reset via email.
- **Admin Panel:** Manage products, categories, and users with secure RESTful endpoints.
- **Product & Category Management:** CRUD operations for products and categories.
- **Email Integration:** SMTP-based password reset and notifications.
- **Logging & Auditing:** Log4j2 integration for detailed application logs.
- **API Documentation:** Interactive Swagger UI and OpenAPI documentation.
- **Security Best Practices:** OWASP guidelines implemented.
- **Monitoring & Metrics:** Real-time application metrics with Prometheus and Grafana.
- **Code Quality:** Automated analysis with SonarQube.
- **Continuous Integration:** Automated build, test, and deployment pipelines.

---

## Architecture

- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf templates (can be replaced with React/Vue)
- **Database:** Microsoft SQL Server
- **Monitoring:** Prometheus & Grafana
- **Quality & Security:** SonarQube, OWASP
- **API Documentation:** Swagger/OpenAPI

---

## Technologies & Tools

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **Thymeleaf**
- **Lombok**
- **Log4j2**
- **Swagger / OpenAPI**
- **OWASP Security Practices**
- **Microsoft SQL Server**
- **JavaDotenv**
- **SonarQube**
- **Prometheus**
- **Grafana**
- **CI/CD:** GitHub Actions, or GitLab CI

---

## Setup & Installation

### Prerequisites

- Java 17 or higher
- Maven
- Microsoft SQL Server
- Docker (optional, for running Prometheus/Grafana/SonarQube)

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/simayaydin/etiya-project.git
   cd etiya-project
   ```

2. **Configure environment variables:**
   - Create a `.env` file in the project root:
     ```
     DB_USERNAME=your_db_username
     DB_PASSWORD=your_db_password
     MAIL_USERNAME=your_email@gmail.com
     MAIL_PASSWORD=your_email_app_password
     ```
   - Or set these variables in your system environment.

3. **Configure the database:**
   - Update `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=etiya_ecommerce
     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     ```

4. **Build and run the application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the application:**
   - Web UI: `http://localhost:8081`
   - Swagger UI: `http://localhost:8081/swagger-ui.html`

---

## Configuration

- **Sensitive Data:** All secrets and passwords are managed via `.env` or environment variables.
- **Logging:** Configured via `log4j2.xml`.
- **Monitoring:** Prometheus and Grafana configuration files are provided in `/monitoring`.
- **Quality:** SonarQube configuration in `/sonar-project.properties`.

---

## API Documentation

- **Swagger UI:** Interactive API docs at `/swagger-ui.html`
- **OpenAPI Spec:** Available at `/v3/api-docs`
- **Endpoints:**  
  - Admin: `/api/admin/*`  
  - User: `/api/user/*`  
  - Auth: `/api/auth/*`  
  - Password Reset: `/api/auth/forgot-password`

---

## Quality & Security

- **SonarQube:** Automated code quality and security analysis.
- **OWASP:** Secure coding practices and vulnerability checks.
- **Unit & Integration Tests:** Located in `src/test/java`.

---

## Monitoring & Observability

- **Prometheus:** Collects application metrics.
- **Grafana:** Visualizes metrics and dashboards.
- **Log4j2:** Centralized logging for troubleshooting.

---

## Continuous Integration

CI/CD pipeline is configured in the `ci-pipeline.yml` file. It includes:
- Maven build
- SonarQube quality gate
- OWASP dependency scanning


---

## Project Structure

```
etiya-project/
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── service/
│   │   │   ├── dto/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   └── test/
├── .env
├── pom.xml
├── monitoring/
│   ├── prometheus.yml
│   └── grafana/
├── sonar-project.properties
├── Dockerfile
└── README.md
```

---

## Usage

- **Admin Endpoints:** `/api/admin/*`
- **User Endpoints:** `/api/user/*`
- **Authentication:** `/api/auth/*`
- **Password Reset:** `/api/auth/forgot-password`

---

## Authors

- **simayaydin**



