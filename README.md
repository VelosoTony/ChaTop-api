# ChaTop-API

![Maven](https://img.shields.io/badge/Apache%20Maven-3.8.7-blueviolet)![Java](https://img.shields.io/badge/Java-17.0.6-red)![JavaScript](https://img.shields.io/static/v1?label=&message=JavaScript&color=yellow)![HTML](https://img.shields.io/static/v1?label=&message=HTML&color=green)

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.1.6, Node version 18.14.0, Package Manager version npm 9.3.1.

## Installation

1. Clone the application
`git clone https://https://github.com/VelosoTony/ChaTop-api.git`\

2. Create MySQL Database
`run src/main/resouces/db_create_chatop.sql`\

3. Change the credential of database access 
open `/src/main/resources/application.properties`
modify `spring.datasource.username` and `spring.datasource.password` as per your MySQL credentials.

4. Run the app using maven
`mvn spring-boot:run`

The API will start running at http://localhost:3001
