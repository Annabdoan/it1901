package beerPunishment.restserver;

import beerPunishment.json.JsonHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


//scanBasePackages={"beerPunishment.core.BeerMain"}

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class RestServerApp {



    public static void main(String[] args) {
        SpringApplication.run(RestServerApp.class, args);
    }
}