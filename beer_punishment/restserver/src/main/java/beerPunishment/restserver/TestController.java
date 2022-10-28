package beerPunishment.restserver;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class TestController {


    @GetMapping("/greeting")
    public String getGreeting() {
        return "Hei Anna";
    }

}