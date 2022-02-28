# Reading Is Good
ReadingIsGood is a project that includes user management, book management and order management. You can do the following features: 

- create user
- create book
- create order(added version in book collection for optimistic locking)
- get all orders of the customer with paging
- get book stock, add book stock
- get order detail
- update order status
- get orders between two dates
- get monthly order statics.

This project has two type of user; base user and admin. Admin access all endpoints but base user cannot access. 

## Tech Stack

- Java 11
- Spring Boot
- Maven
- MongoDB
- Docker
- Swagger
- Spring Security
- Lombok
- Git
- Intellij IDEA

## How to start the project

Type the following commands to project path:

```sh
mvn clean package

docker build -t readingisgood .

docker pull mongo

docker run -d --name mongodb -p 27017:27017 mongo

docker run -p 8080:8080 --name readingisgood --link mongodb:getirdb -d readingisgood
```

After all commands, project will be up and running. You can access to endpoints with postman or swagger(http://localhost:8080/swagger-ui/index.html#/).

Firstly you have to sign up with this endpoint "/user/signUp". You must enter mail:"burcakozgul@gmail.com" for admin role. Other mails will be base user.
After sign up, you can sign in with your mail and password with this endpoint "/auth/signIn". Sign in returns token. You must use this token for access endpoints. If base user try to access those endpoints("/order/orderStatus", "/book", put "/book/stock/{bookId}", /statistics/{userId}), response return error.  
