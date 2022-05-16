package com.example.batch.service;

import com.example.batch.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {
//        list.stream().forEach(System.out::println);
//        System.out.println("chunk written");
        for (Product product : list) {
            String name = product.getTitle();
            String price = product.getPrice();
            Double priceVal = 0.0d;
            if (StringUtils.hasText(price)) {
                priceVal = Double.parseDouble(price.substring(1));
            }
            String image = "";
            if (product.getImageURLHighRes() != null && product.getImageURLHighRes().size() > 0) {
                image= product.getImageURLHighRes().get(0);
            }


            jdbcTemplate.update("insert into products(name,price,image) values (?,?,?)",
                    StringUtils.hasText(name) ? name : UUID.randomUUID(),
                    priceVal,
                    StringUtils.hasText(image) ? image : "");
        }
    }
}
