package com.example.batch.service;

import com.example.batch.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;



public class ProductProcessor implements ItemProcessor<JsonNode, Product>, StepExecutionListener {
    private final Logger logger = LoggerFactory.getLogger(ProductProcessor.class);

    private ObjectMapper objectMapper;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public Product process(JsonNode jsonNode) throws Exception {
        System.out.println(jsonNode);
        Product ret = objectMapper.treeToValue(jsonNode, Product.class);
        System.out.println(ret);
        return ret;
    }
}
