package beerPunishment.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * The Spring application.
 */
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class RestServerApp {

    public static void main(String[] args) {
        SpringApplication.run(RestServerApp.class, args);
    }
}