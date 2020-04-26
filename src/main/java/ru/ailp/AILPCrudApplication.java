package ru.ailp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AILPCrudApplication {

    public static void main(String[] args) {
        log.info("AILPCrudApplication starting");
        SpringApplication.run(AILPCrudApplication.class, args);
    }
}
