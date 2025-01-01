package com.arturfrimu.firstdeploy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@SpringBootApplication
public class FirstDeployApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstDeployApplication.class, args);
    }

    private final ItemRepository itemRepository;

    @GetMapping
    public List<Item> items() {
        return itemRepository.findAll();
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

        public Item(String text) {
            this.text = text;
        }
    }

    @Slf4j
    @Service
    @RequiredArgsConstructor
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    static class ItemService implements CommandLineRunner {

        ItemRepository itemRepository;

        @Override
        public void run(String... args) {
            List<Item> items = IntStream.range(1, 11)
                    .boxed()
                    .map(index -> new Item("Item " + index))
                    .toList();

            itemRepository.saveAll(items);
        }
    }
}