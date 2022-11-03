package beerPunishment.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


//Application contains the start-method for the server application.

@SpringBootApplication
public class RestServerApp {
    public static void main(String[] args) {
        SpringApplication.run(RestServerApp.class, args);
    }
}