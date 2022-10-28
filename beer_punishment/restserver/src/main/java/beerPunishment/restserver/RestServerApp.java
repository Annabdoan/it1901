package beerPunishment.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;




@SpringBootApplication
public class RestServerApp {
    public static void main(String[] args){
        SpringApplication.run(RestServerApp.class, args);
    }

   /* public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("") //Add in path
                        .allowedOrigins("",""); //Add in path + URL
                        .allowedMethods("PUT", "POST", "GET", "DELETE");
            }
        }

    }*/


}
