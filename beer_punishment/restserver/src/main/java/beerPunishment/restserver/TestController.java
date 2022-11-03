package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


//Controller makes it possible for the server to listen to HTTP-requests and process them.

@RestController
public class TestController {


    private BeerMain beerMain = new BeerMain();
    private Rule rule = new Rule("Komme for sent", 5);
    private Rule rule2 = new Rule("Banne", 10);


    //If you take in @RequestParam you; see video 16:20
    @GetMapping(path = "rules")
    public Collection<Rule>  getRules() {
        beerMain.addRule(rule);
        return beerMain.getRules();
    }

    @GetMapping("/memberRuleViolations")
    public HashMap<String, Collection<Rule>> getMemberRuleViolations() {
        beerMain.addMember("Lea");
        return beerMain.getMemberRuleViolations();
    }

    @GetMapping("/members")
    public Collection<String> getMembers() {
       Collection<String> members = new ArrayList<>();
       beerMain.addMember("Sara");
       beerMain.addMember("Maurice");
       beerMain.addMember("Anna");
       for (String key : beerMain.getMemberRuleViolations().keySet()) {
           members.add(key);
       }
        return members;
    }
    //Denne funker ikke enda..
    //localhost:8080/addmember?name=name
    /*@PostMapping("/addmember")
    public void registerNewMember(@RequestParam(value = "name") String name) {
        try{
            beerMain.addMember(name);
        } catch(Exception e) {
            throw new IllegalArgumentException("Brukernavnet finnes allerede/Inntasting feil");
        }

    }*/
    @PostMapping
    public void addRule(@RequestBody Rule rule2) {
        beerMain.addRule(rule2);
    }

    @PutMapping(path = "/addMember", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMember(@RequestBody BeerMain beerMain,
                                     @RequestParam("name") String name) {
        beerMain.addMember(name);
    }


    //@PutMapping("")

    //@DeleteMapping("")
}