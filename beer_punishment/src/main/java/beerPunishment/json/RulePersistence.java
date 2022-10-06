package beerPunishment.json;

import beerPunishment.core.Rule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.Set;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.FileWriter;
import beerPunishment.json.RuleModule;


public class RulePersistence {
    /**
     * Used to indicate what parts of a RuleModel to serialize.
     */
    public enum RuleModelParts {
        SETTINGS, LISTS, LIST_CONTENTS
    }

    private ObjectMapper mapper;

    public RulePersistence() {
        mapper = createObjectMapper();
    }
    public static SimpleModule createJacksonModule(Set<RuleModelParts> parts) {
        return new RuleModule(parts);
    }
    public static ObjectMapper createObjectMapper(Set<RuleModelParts> parts) {
        return new ObjectMapper()
                .registerModule(createJacksonModule(parts));
    }
    public static ObjectMapper createObjectMapper() {
        return createObjectMapper(EnumSet.allOf(RuleModelParts.class));
    }

    public RuleModel readRuleModel(Reader reader) throws IOException {
        return mapper.readValue(reader, RuleModel.class);
    }

    public void writeRuleModel(RuleModel ruleModel, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, ruleModel);
    }

    private Path saveFilePath = null;

    public void setSaveFile(String saveFile) {
        this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
    }

    public Path getSaveFilePath() {
        return this.saveFilePath;
    }

    /**
     * Loads a RuleModel from the saved file (saveFilePath) in the user.home folder.
     *
     * @return the loaded RuleModel
     */
    public RuleModel loadRuleModel() throws IOException, IllegalStateException {
        if (saveFilePath == null) {
            throw new IllegalStateException("Save file path is not set, yet");
        }
        try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
            return readRuleModel(reader);
        }
    }

    /**
     * Saves a RuleModel to the saveFilePath in the user.home folder.
     *
     * @param ruleModel the RuleModel to save
     */
    public void saveRuleModel(RuleModel ruleModel) throws IOException, IllegalStateException {
        if (saveFilePath == null) {
            throw new IllegalStateException("Save file path is not set, yet");
        }
        try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
            writeRuleModel(ruleModel, writer);
        }
    }



}
