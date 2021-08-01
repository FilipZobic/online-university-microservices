
University project for Software and Data Engineering covering multiple subjects:
* Internet software architecture
* Client Web Application's
* Web programming
* Web based informational systems

### Singidunum University - Center Novi Sad

Professor: Djordje Obradović

Assistant Professor: Ivan Radosavljević

Student: Filip Zobić - 2019270036

Student: Stevan Pataki - 2018270719

### Architecture

The backend has been implemented with the Spring Boot framework.

The API is following the Microservice Architecture.

The API is following Onion Architecture design pattern.

Authentication has been implemented using JWT tokens.

Relational database of choice is PostgreSql.

Messaging queue is RabbitMQ.

### Services:
* cloud-server - cloud discovery server
### Installation:
#### Requirements:
* RabbitMQ
* PostgreSql
    * Configure System ENV variables (see application.yml)
* Java 11