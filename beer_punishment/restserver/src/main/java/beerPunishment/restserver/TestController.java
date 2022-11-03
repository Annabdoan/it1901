package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashMap;


//Controller makes it possible for the server to listen to HTTP-requests and process them.

@RestController
public class TestController {

    private BeerMain beerMain = new BeerMain();
    private Rule rule = new Rule("Komme for sent", 5);


    //If you take in @RequestParam you; see video 16:20
    @GetMapping("/rules")
    public Collection<Rule> beerMain() {
        beerMain.addRule(rule);
        return beerMain.getRules();
    }

    @GetMapping("/members")
    public HashMap<String, Collection<Rule>> beerMain2() {
        beerMain.addMember("Lea");
        return beerMain.getMemberRuleViolations();
    }


    //@PostMapping("")


    //@PutMapping("")

    //@DeleteMapping("")
}