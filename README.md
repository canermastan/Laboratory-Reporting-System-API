
# Laboratory Reporting Application

This project is a web application developed for the management of laboratory reports. The backend is built using Java and Spring Boot, while the frontend is developed with React. The project supports functions such as creating, updating, deleting, and listing reports. Additionally, secure login is provided via JWT.

## Other Repository

You can access the frontend code used for this project [here](https://github.com/canermastan/Laboratory-Reporting-System-UI).

## Installation

### Java (Backend)
```bash
./mvnw spring-boot:run
```
The project is configured to work with a PostgreSQL database. You can update the database settings in the `application.properties` file. The PostgreSQL settings are:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=root
```

### User Information
When the application starts, admin and user accounts are automatically created. The user information is as follows:

#### Admin
```
Email: admin@test.com
Password: 123456
```

#### User
```
Email: user@test.com
Password: 123456
```

### Technical Details
#### UI Library
This application uses Semantic UI to help developers understand the code quickly and effectively. Semantic UI has a clear structure and its classes and tags are semantically named. It is also preferred because it has comprehensive documentation.

#### Authentication Mechanism
This application uses JSON Web Token (JWT) for secure user authentication. JWT is a widely used authentication standard in modern web applications.

#### Data Transfer Object (DTO)
DTO (Data Transfer Object) is a design pattern optimized for data transfer. It effectively transfers data objects in communication with databases or external services. DTOs reduce network traffic, improve client application performance, and ensure that only required data is accessed. In this application, the DTO design pattern is used to simplify users' access to only the necessary data.

#### Cache Mechanism
In REST APIs, a cache mechanism is used to enhance performance and reduce server load. A cache is a memory area where data or responses are temporarily stored. While this application does not use a custom cache mechanism, Spring's cache capabilities are utilized to some extent, considering performance requirements.

## Endpoints

### Authentication

**POST /auth/register**

User registers to the system.

Request Body:
```json
{
  "firstName": "",
  "lastName": "",
  "email": "",
  "password": "",
  "hospitalIdentityNumber": ""
}
```

**POST /auth/login**

User logs in to the system.

Request Body:
```json
{
  "email": "user@test.com",
  "password": "123456"
}
```

### Image
```
POST /image/upload/{reportId}
```

Defines a photo for the report.

```
GET /image/download/{fileId}
```

Retrieves the photo defined for the report.

### Report
```
GET /api/v1/report/all
```
Fetches all reports.

```
GET /api/v1/report/{id}
```

Fetches the report matching the ID.

****

**POST /api/v1/report/save**

Request Body:
```json
{
  "reportNo": "",
  "patientFirstName": "",
  "patientLastName": "",
  "patientIdentityNumber": "",
  "diagnosisTitle": "",
  "diagnosisDetail": ""
}
```

Adds a new report.

```
PUT /api/v1/report/upload/{id}
```

Updates the report matching the ID.

```
DELETE /api/v1/report/delete/{id}
```

Deletes the report matching the ID (only admin users can perform this action).
