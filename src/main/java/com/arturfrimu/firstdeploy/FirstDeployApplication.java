package com.arturfrimu.firstdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
