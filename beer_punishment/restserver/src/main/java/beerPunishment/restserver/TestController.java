package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;

import beerPunishment.json.JsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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

    private Rule rule = new Rule();


    @GetMapping(path = "beerMain")
    public BeerMain getBeerMain() {
        return beerMain;
    }


    @GetMapping(path = "rules")
    public Collection<Rule>  getRules() {
        return beerMain.getRules();
    }

    @GetMapping("/memberRuleViolations")
    public HashMap<String, Collection<Rule>> getMemberRuleViolations() {
        return new HashMap<>(beerMain.getMemberRuleViolations());

    }

    @GetMapping("/members")
    public Collection<String> getMembers() {
        return beerMain.getUsernames();
    }


    @PostMapping(path = "/addRule")
    public void addRule(@RequestParam("description") String ruleDescription, @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription,value);
        beerMain.addRule(rule);
    }

    @PostMapping(path = "/addMember")
    public void addMember(@RequestParam("name") String name) {
        beerMain.addMember(name);
    }

    @PutMapping(path="punishMember")
    public void punishMember(@RequestParam("member") String member,
                             @RequestParam("description") String ruleDescription,
                             @RequestParam("value") int value){
        this.rule = new Rule(ruleDescription,value);
        beerMain.punishMember(member,this.rule);
    }


    @DeleteMapping(path = "removeRule")
    public void removeRule(@RequestParam("rule") String ruleDescription){
        beerMain.removeRuleUsingDescription(ruleDescription);
    }

    @DeleteMapping(path = "deleteMember")
    public void deleteMember(@RequestParam("member") String member) {
        beerMain.deleteMember(member);
    }

}