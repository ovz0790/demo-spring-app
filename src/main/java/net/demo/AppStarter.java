package net.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppStarter {
    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(AppStarter.class, args);
    }

}
