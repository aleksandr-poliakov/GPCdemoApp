package com.example.gpcdemoapp.config;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.dto.StatementDTO;
import com.example.gpcdemoapp.dto.TransactionDTO;
import com.example.gpcdemoapp.services.PaymentService;
import com.example.gpcdemoapp.services.StatementService;
import com.example.gpcdemoapp.services.TransactionService;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Configuration
public class InitDB implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitDB.class);
    private final ResourceLoader resourceLoader;
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final StatementService statementService;

    public InitDB(ResourceLoader resourceLoader, PaymentService paymentService, TransactionService transactionService, StatementService statementService) {
        this.resourceLoader = resourceLoader;
        this.paymentService = paymentService;
        this.transactionService = transactionService;
        this.statementService = statementService;
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyy");

    public static StatementDTO parseStatementRecord(String line) throws ParseException {
        StatementDTO statementRecord = new StatementDTO();

        statementRecord.setType(line.substring(0, 3));
        statementRecord.setAccountNumber(line.substring(3, 19));
        statementRecord.setTruncatedAccountName(line.substring(19, 39));
        statementRecord.setOldBalanceDate(DATE_FORMAT.parse(line.substring(39, 45)));
        statementRecord.setOldBalance(Double.parseDouble(line.substring(45, 59)));
        statementRecord.setOldBalanceSign(line.charAt(59));
        statementRecord.setNewBalance(Double.parseDouble(line.substring(60, 73)));
        statementRecord.setNewBalanceSign(line.charAt(74));
        statementRecord.setDebitTurnovers(Double.parseDouble(line.substring(75, 88)));
        statementRecord.setDebitTurnoversSign(line.charAt(89));
        statementRecord.setCreditTurnovers(Double.parseDouble(line.substring(90, 103)));
        statementRecord.setCreditTurnoversSign(line.charAt(104));
        statementRecord.setSerialNumber(line.substring(106, 108));
        statementRecord.setAccountingDate(DATE_FORMAT.parse(line.substring(108, 114)));

        return statementRecord;
    }

    public static TransactionDTO parseTransactionItemRecord(String line) throws ParseException {
        TransactionDTO transactionItemRecord = new TransactionDTO();

        transactionItemRecord.setType(line.substring(0, 3));
        transactionItemRecord.setAssignedAccountNumber(line.substring(3, 19));
        transactionItemRecord.setAccountNumber(line.substring(19, 35));
        transactionItemRecord.setDocumentNumber(line.substring(35, 48));
        transactionItemRecord.setAmount(Double.parseDouble(line.substring(48, 60)));
        transactionItemRecord.setAccountingCode(line.charAt(60));
        transactionItemRecord.setVariableSymbol(line.substring(61, 71));
        transactionItemRecord.setConstantSymbol(line.substring(72, 82));
        transactionItemRecord.setSpecificSymbol(line.substring(82, 92));
        transactionItemRecord.setCurrency(line.substring(92, 97));
        transactionItemRecord.setClientName(line.substring(97, 117).trim());
        transactionItemRecord.setZero(line.charAt(116));
        transactionItemRecord.setCurrencyCode(line.substring(119, 122));
        transactionItemRecord.setDueDate(DATE_FORMAT.parse(line.substring(122, 128)));

        return transactionItemRecord;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource gpcDocResource = resourceLoader.getResource("classpath:Shoptet_Pay_statement_2022-01-15.gpc");
        try (BufferedReader reader = new BufferedReader(new FileReader(gpcDocResource.getFile().getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("074")) {
                    statementService.saveAndFlush(parseStatementRecord(line));
                } else if (line.startsWith("075")) {
                    transactionService.saveAndFlush(parseTransactionItemRecord(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
