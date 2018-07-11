package com.tactfactory.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Main application.
 */
@Slf4j
@SpringBootApplication()
public class IotApplication {

    public static void main(String[] args) {
        log.debug("Main context initialization.");

        SpringApplication.run(IotApplication.class, args);

        log.debug("Application initialize finished.");
    }
}
