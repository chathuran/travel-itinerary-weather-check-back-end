# travel-itinerary-weather-check-back-end


## Requirements
- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally


Change configurations accordingly in  `src.main.java.resources.application.properties`



## Run Project

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.chathuran.weather.check.travelweathercheck.TravelWeatherCheckApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Then you can access http://localhost:8090/ or you can change `server.port` value in `application.properties` file.


# Build and deployment

```shell
mvn clean package
```

This will generate .jar file

1 Copy  the .jar file into server location
    when copying jar file copy the `src.main.java.resources.application.properties` file in to the same location that jar file located
2. deploy .jar file using java command

   ````
   java -jar <app-name>.jar --spring.config.location=file:/<path to ,jar file>/application.properties
