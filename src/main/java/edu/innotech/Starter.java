package edu.innotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "edu.innotech")
@PropertySource({"application.properties"})
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}