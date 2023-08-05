package com.example.gpcdemoapp.config;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.services.PaymentService;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Configuration
public class InitDB implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitDB.class);
    ResourceLoader resourceLoader;
    PaymentService paymentService;

    public InitDB(ResourceLoader resourceLoader, PaymentService paymentService) {
        this.resourceLoader = resourceLoader;
        this.paymentService = paymentService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:gpc.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            int inserted = 0;
            int duplicate = 0;
            int checked = 0;
            logger.info("Start check and insert");
            if (reader.readNext().length == 0) {
                return;
            }
            while ((nextLine = reader.readNext()) != null) {
                var payment = new PaymentDTO();
                payment.setSenderAccount(nextLine[1]);
                payment.setRecipientAccount(nextLine[2]);
                payment.setAmount(Float.valueOf(nextLine[3]));

                if (!paymentService.existByAccount(payment.getSenderAccount(),
                        payment.getRecipientAccount())) {
                    paymentService.saveAndFlush(payment);
                    inserted++;
                }else {
                    duplicate++;
                }

                checked++;
            }
            logger.info("Checked: " + checked +", Inserted: "+ inserted+", Duplicate: "+ duplicate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
