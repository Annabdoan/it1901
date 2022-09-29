package beerPunishment.json;

import beerPunishment.core.Rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    
    public List<Rule> readRules(String filename) throws FileNotFoundException {

        List<Rule> rules = new ArrayList<>();


        try(Scanner scanner = new Scanner(getFile(filename))) {
            while (scanner.hasNextLine()){
                try {
                    String[] scannerValues = scanner.nextLine().split(";");
                    String description = scannerValues[0];
                    int punishmentValue = Integer.valueOf(scannerValues[1]);
                    rules.add(new Rule(description, punishmentValue));    
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid file format");
                }
            }  
        } 
        return new ArrayList<>(rules);
    }

    public void writeRule(String filename, Rule rule) throws IOException {
        
        //Bruker Filewriter for å appende til filen, ikke overkjøre det som allerede står der. Dette er hensiktsmessig i et arkiv.
        try (PrintWriter writer = new PrintWriter(new FileWriter(getFile(filename), true))){
            writer.println(rule.getDescription() + ";" + String.valueOf(rule.getPunishmentValue()));
        } 
    }

    public File getFile(String filename){
        return new File(filename + ".txt");
    }
}