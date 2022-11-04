package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;

import beerPunishment.json.JsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;




import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


//Controller makes it possible for the server to listen to HTTP-requests and process them.

@RestController
//@RequestMapping(TestController.BEER_PUNISHMENT_SERVICE_PATH)
public class TestController {



    private BeerMain beerMain = new BeerMain();
    //beermain = jsh.readFromJson(fileName);
    //Skal lese fra JSON filen.


    private BeerMain beerMain2 = new BeerMain();


    @GetMapping(path = "beerMain")
    public BeerMain getBeerMain() {
        return beerMain;
    }

    /*@GetMapping(path = "rules")
    public Collection<Rule>  getRules() {
        //hente ut beermain objektet vårt
        return beerMain.getRules();
    }

    @GetMapping("/ruletest")
    public Rule rule(){
        return new Rule("Komme for sent", 5);
    }

    @GetMapping("/memberRuleViolations")
    public HashMap<String, Collection<Rule>> getMemberRuleViolations() {
        return beerMain.getMemberRuleViolations();
    }

    @GetMapping("/members")
    public Collection<String> getMembers() {
       Collection<String> members = new ArrayList<>();
       beerMain2.addMember("Sara");
       beerMain2.addMember("Maurice");
       beerMain2.addMember("Anna");
       for (String key : beerMain2.getMemberRuleViolations().keySet()) {
           members.add(key);
       }
        return members;
    }*/

    @PostMapping
    public void addRule(@RequestBody Rule rule2) {
        beerMain.addRule(rule2);
    }

    @PutMapping(path = "/addMember")
    public void addMember(@RequestParam("name") String name) {
        beerMain.addMember(name);
    }


    //@PutMapping("")

    //@DeleteMapping("")
}