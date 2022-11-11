package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;

import beerPunishment.json.JsonHandler;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@RestController

public class BeerMainRestController {
    private BeerMain beerMain = BeerMainService.createBeerMain();
    private Rule rule = new Rule();

    private JsonHandler jsh = new JsonHandler();

    public void writeToJson() { //Trenger i test derfor public
        try {
            jsh.writeToJson(this.beerMain, "/beerPunishmentRemote.json");
        }catch (IOException IOE) {
            System.out.println("Error while writing to file");
        }
    }

    @GetMapping(path = "beerMain")
    public BeerMain getBeerMain() {
        return this.beerMain;
    }


    @PostMapping(path = "members")
    public void addMember(@RequestParam("name") String name) {
        this.beerMain.addMember(name);
        writeToJson();
    }

    @DeleteMapping(path = "members")
    public void deleteMember(@RequestParam("member") String member) {
        this.beerMain.deleteMember(member);
        writeToJson();
    }
    @PostMapping(path = "rules")
    public void addRule(@RequestParam("description") String ruleDescription, @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.addRule(rule);
        writeToJson();
    }
    @DeleteMapping(path = "rules")
    public void removeRule(@RequestParam("rule") String ruleDescription){
        this.beerMain.removeRuleUsingDescription(ruleDescription);
        writeToJson();
    }


    @PutMapping(path="punishMember")
    public void punishMember(@RequestParam("member") String member,
                             @RequestParam("description") String ruleDescription,
                             @RequestParam("value") int value){
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.punishMember(member,this.rule);
        writeToJson();
    }




    @DeleteMapping(path = "payPunishment")
    public void payPunishment(@RequestParam("member") String member,
                              @RequestParam("description") String ruleDescription,
                              @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription, value);
        this.beerMain.removePunishment(member, rule);
        writeToJson();
    }

    @GetMapping(path = "ping")
    public String ping() {
        return "pong";
    }

}