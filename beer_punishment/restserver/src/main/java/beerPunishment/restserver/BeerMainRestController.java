package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;

import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.HashMap;


//Controller makes it possible for the server to listen to HTTP-requests and process them.

@RestController
//@RequestMapping(TestController.BEER_PUNISHMENT_SERVICE_PATH)
public class BeerMainRestController {
    private BeerMain beerMain = BeerMainService.createBeerMain();
    private Rule rule = new Rule();


    @GetMapping(path = "beerMain")
    public BeerMain getBeerMain() {
        return this.beerMain;
    }


    @GetMapping(path = "rules")
    public Collection<Rule>  getRules() {
        return this.beerMain.getRules();
    }

    @GetMapping("/memberRuleViolations")
    public HashMap<String, Collection<Rule>> getMemberRuleViolations() {
        return new HashMap<>(this.beerMain.getMemberRuleViolations());

    }

    @GetMapping("/members")
    public Collection<String> getMembers() {
        return this.beerMain.getUsernames();
    }


    @PostMapping(path = "/addRule")
    public void addRule(@RequestParam("description") String ruleDescription, @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.addRule(rule);
    }

    @PostMapping(path = "/addMember")
    public void addMember(@RequestParam("name") String name) {
        this.beerMain.addMember(name);
    }

    @PutMapping(path="punishMember")
    public void punishMember(@RequestParam("member") String member,
                             @RequestParam("description") String ruleDescription,
                             @RequestParam("value") int value){
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.punishMember(member,this.rule);
    }


    @DeleteMapping(path = "removeRule")
    public void removeRule(@RequestParam("rule") String ruleDescription){
        this.beerMain.removeRuleUsingDescription(ruleDescription);
    }

    @DeleteMapping(path = "deleteMember")
    public void deleteMember(@RequestParam("member") String member) {
        this.beerMain.deleteMember(member);
    }

    @DeleteMapping(path = "payPunishment")
    public void payPunishment(@RequestParam("member") String member,
                              @RequestParam("description") String ruleDescription,
                              @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription, value);
        this.beerMain.removePunishment(member, rule);
    }
}