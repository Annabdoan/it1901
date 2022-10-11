package beerPunishment.json;

import beerPunishment.core.Rule;
import beerPunishment.core.RuleModel;
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
    private Path filePath;

    public RulePersistence() {
        mapper = createObjectMapper();
    }
    public static SimpleModule createJacksonModule(Set<RulePersistence.RuleModelParts> parts) {
        return new RuleModule(parts);
    }
    public static ObjectMapper createObjectMapper(Set<RulePersistence.RuleModelParts> parts) {
        return new ObjectMapper()
                .registerModule(createJacksonModule(parts));
    }
    public static ObjectMapper createObjectMapper() {
        return createObjectMapper(EnumSet.allOf(RulePersistence.RuleModelParts.class));
    }

    public Rule readRule(Reader reader) throws IOException {
        return mapper.readValue(reader, Rule.class);
    }

    public void writeRule(Rule rule, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, rule);
    }

    public void saveRule(Rule rule) throws IOException, IllegalStateException {
        if (saveFilePath == null) {
            throw new IllegalStateException("Save file path is not set, yet");
        }
        try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
            writeRule(rule, writer);
        }
    }
    public Rule loadRule() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("Save file path is not set, yet");
        }
        try (Reader reader = new FileReader(filePath.toFile(), StandardCharsets.UTF_8)) {
            return readRule(reader);
        }
    }

    private Path saveFilePath = null;

    public void setSaveFile(String saveFile) {
        this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
    }

    public Path getSaveFilePath() {
        return this.saveFilePath;
    }


}
