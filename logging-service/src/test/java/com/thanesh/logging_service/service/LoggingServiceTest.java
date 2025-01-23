package com.thanesh.logging_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoggingServiceTest {

    private LoggingService loggingService;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService();
    }

    @Test
    void processLog() {
        loggingService.processLog();
    }
}