# Spring Boot java users service with JWT Security

This project is a simple users service built with Spring Boot. It uses JWT for authentication and authorization, enabling secure access to protected resources.

## Features
- User authentication and JWT generation
- User roles with restricted access using `@PreAuthorize`
- Secure endpoints for operations (like creating accounts, transactions, etc.)
- User registration and management

## Technologies Used
- **Spring Boot**
- **JWT (JSON Web Token)**
- **Spring Security**
- **Hibernate & MySQL**

## Endpoints Overview
| Endpoint                     | Method | Role      | Description                         |
|------------------------------|--------|-----------|-------------------------------------|
| `/auth/generateToken`         | POST   | Public    | Generates JWT for authentication    |
| `/auth/addNewUser`            | POST   | Admin     | Registers a new user                |
| `/auth/welcome`               | GET    | Public    | Welcome page                        |
| `/user/account/create`        | POST   | User      | Creates a new bank account          |
| `/admin/users`                | GET    | Admin     | Lists all users (Admin role)        |

## How to Run the Project

### Prerequisites
1. Java 17+
2. MySQL installed and running
3. Maven installed

### Steps to Set Up:
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/spring-boot-bank-api.git
   cd spring-boot-bank-api
