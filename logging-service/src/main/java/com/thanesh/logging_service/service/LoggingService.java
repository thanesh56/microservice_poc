package com.thanesh.logging_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);

    public static final String LOG_FILE_PATH = "/Users/thaneshwarsahu/logs/employee-service.log";

    public LoggingService() {
        log.info("LoggingService initialized...");
    }

    @Scheduled(fixedRate = 60000)
    public void processLog() {
        log.info("LoggingService::processLog scheduler triggered {} ", LocalDateTime.now());
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Simulate pushing logs to a Centralized system
                // such as Splunk, ELK, ES
                log.info("Processing Logs: {} ", line);
            }
            log.info("LoggingService::processLog ended");
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("LoggingService::processLog ended due to exception", ex);
        }
    }
}
