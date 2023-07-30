package me.timur.touroperatorbpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TourOperatorBpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourOperatorBpaApplication.class, args);
    }

}
