package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.JsonHandler;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;


/**
 * The service implementation.
 */
@RestController
public class BeerMainRestController {
    private BeerMain beerMain = BeerMainService.createBeerMain();
    private Rule rule = new Rule();

    private JsonHandler jsh = new JsonHandler();

    /**
     * Writes beerMain to Json-file.
     */
    public void writeToJson() {
        try {
            jsh.writeToJson(this.beerMain, "/beerPunishmentRemote.json");
        }catch (IOException IOE) {
            System.out.println("Error while writing to file");
        }
    }

    /**
     * Get the corresponding beerMain.
     *
     * @return a copy of the corresponding beerMain object
     */
    @GetMapping(path = "beerMain")
    public BeerMain getBeerMain() {
        BeerMain beerMainCopy = new BeerMain();
        beerMainCopy.copy(this.beerMain);
        return beerMainCopy;
    }

    /**
     * Adds new member.
     *
     * @param name name of new member
     */
    @PostMapping(path = "members")
    public void addMember(@RequestParam("name") String name) {
        this.beerMain.addMember(name);
        writeToJson();
    }

    /**
     * Deletes a member.
     *
     * @param member member to delete
     */
    @DeleteMapping(path = "members")
    public void deleteMember(@RequestParam("member") String member) {
        this.beerMain.deleteMember(member);
        writeToJson();
    }

    /**
     * Adds a rule.
     *
     * @param value number of beers
     * @param ruleDescription description of new rule
     */
    @PostMapping(path = "rules")
    public void addRule(@RequestParam("description") String ruleDescription, @RequestParam("value") int value) {
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.addRule(rule);
        writeToJson();
    }

    /**
     * Removes a rule.
     *
     * @param ruleDescription description of rule to delete
     */
    @DeleteMapping(path = "rules")
    public void removeRule(@RequestParam("rule") String ruleDescription){
        this.beerMain.removeRuleUsingDescription(ruleDescription);
        writeToJson();
    }

    /**
     * Punishes a member.
     *
     * @param member member to punish
     * @param ruleDescription description of rule violated
     * @param value number of beers in punishment
     */
    @PutMapping(path="punishMember")
    public void punishMember(@RequestParam("member") String member,
                             @RequestParam("description") String ruleDescription,
                             @RequestParam("value") int value){
        this.rule = new Rule(ruleDescription,value);
        this.beerMain.punishMember(member,this.rule);
        writeToJson();
    }

    /**
     * Deletes a given punishment.
     *
     * @param member member paying punishment
     * @param ruleDescription description of rule to be paid
     * @param value number of beers being paid
     */
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