# ChaTop-API

![Java](https://img.shields.io/badge/Java-17.0.6-red) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.4-green) ![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-darkgreen) ![Maven](https://img.shields.io/badge/Apache%20Maven-3.8.7-blueviolet) ![MySQL](https://img.shields.io/badge/MySQL-5.6.x-orange) 

ChaTop API is the back-end part of an applicaton to link future tenants with the owners of seasonal rental.

This RestFul CRUD API is using Spring Boot, Mysql, JPA

## Prerequisites
- This project run on **Java 17**, download [here](https://www.oracle.com/fr/java/technologies/downloads/).

- **Maven** is needed to build the project, download [here](https://maven.apache.org/download.cgi).

- The database is manage on **MySQL**, download [here](https://dev.mysql.com/downloads/installer/).
## Getting Started

### 1. Clone the github repository

`git clone https://github.com/VelosoTony/ChaTop-api.git`

### 2. Create MySQL Database

Start MySQL, connect to MySQL Command Line or GUI tool.

Run the script below to create the **chatop** database.

`src/main/resouces/db_create_chatop.sql`

### 3. Set the credential of database access of API

edit `/src/main/resources/application.properties`

modify `spring.datasource.username` and `spring.datasource.password` with MySQL user/password.

### 4. Run the app using maven

`mvn spring-boot:run`
*/!\ To run this command you need to be in the directory "\api" containing the file "pom.xml".*

The application will start running at http://localhost:3001.

# Documentation

The documentation is base on the **springdoc-openapi** library.
For more information you can look [here](https://springdoc.org/v2/).

While the Api is running, 

Documentation will be available at the following url in HTML format :

http://localhost:3001/swagger-ui/index.html

Documentation will be available at the following url for json format :

http://localhost:3001/v3/api-docs

