package com.example.batch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileReader implements StepExecutionListener, ItemReader<JsonNode> {
    private final Logger logger = LoggerFactory.getLogger(JsonFileReader.class);

    private BufferedReader reader;

    private ObjectMapper objectMapper;

    private String fileName;

    public JsonFileReader(String file) {
        if (file.matches("^file:(.*)"))
            file = file.substring(file.indexOf(":") + 1);
        this.fileName = file;
        System.out.println("JsonFileReader: " + file);
    }

    private void initReader() throws FileNotFoundException {
        File file = new File(fileName);
        reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("before Step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public JsonNode read() throws Exception {
        logger.info("read");
        if (objectMapper == null)
            objectMapper = new ObjectMapper();

        if (reader == null) {
            initReader();
        }


        String line = reader.readLine();
        System.out.println(line);
        if (line != null)
            return objectMapper.readTree(line);
        else
            return null;
    }
}
