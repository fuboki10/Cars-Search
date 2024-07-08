## Running Using Docker

To run the application using Docker, you need to have Docker installed on your machine. If you don't have Docker installed, you can download it from [here](https://www.docker.com/products/docker-desktop).

- run the background services using the following command:
```shell docker-compose -f docker-compose.background.yaml up -d```

- run the application using the following command:
```shell docker-compose -f docker-compose.yaml up```

## Running Locally

To run the application locally, you need to have the following installed on your machine:

- Java 17
- Maven
- MongoDB
- Redis

- Start MongoDB and Redis services on your machine.
- edit the `application.properties` file in the `src/main/resources` directory to match your MongoDB and Redis configurations.
- run the application using the following command:
```shell mvn spring-boot:run```
- open your browser and navigate to `http://localhost:8080` to access the application.