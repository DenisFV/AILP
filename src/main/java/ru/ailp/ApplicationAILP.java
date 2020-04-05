package ru.ailp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationAILP {

    private static final Logger logger = LogManager.getLogger(ApplicationAILP.class);

    public static void main(String[] args) {
        logger.info("ApplicationAILP starting");
        SpringApplication.run(ApplicationAILP.class, args);
    }
}