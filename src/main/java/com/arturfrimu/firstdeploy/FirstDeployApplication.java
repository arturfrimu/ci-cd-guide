package com.arturfrimu.firstdeploy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static jakarta.persistence.GenerationType.IDENTITY;

@RestController
@CrossOrigin("*")
@SpringBootApplication
public class FirstDeployApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstDeployApplication.class, args);
    }

    @GetMapping
    public String up() {
        return "I'm UP!";
    }

    @Entity
    @Table(name = "item")

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Item {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;
        private String text;
    }
}
