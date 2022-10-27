package beerPunishment.restserver;

import org.springframework.boot.SpringApplication;
@SpringBootApplication
public class RestServerApp {
    public static void main(String[] args){
        SpringApplication.run(RestServerApp.class, args);
    }

    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("") //Add in path
                        .allowedOrigins("",""); //Add in path + URL
                        .allowedMethods("PUT", "POST", "GET", "DELETE");
            }
        }

    }


}
