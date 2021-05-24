package com.dev.blackmango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BlackmangoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackmangoApplication.class, args);
    }

}
