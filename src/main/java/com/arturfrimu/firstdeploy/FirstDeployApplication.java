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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;


@CrossOrigin("*")
@SpringBootApplication
public class FirstDeployApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstDeployApplication.class, args);
    }

    @RestController
    @RequiredArgsConstructor
    public static class ItemRestController {
        private final ItemRepository itemRepository;

        @GetMapping
        public List<Item> items() {
            return itemRepository.findAll();
        }
    }

    @Controller
    @RequiredArgsConstructor
    @RequestMapping("/items")
    public static class ItemController {
        private final ItemRepository itemRepository;

        @GetMapping
        public String items(Model model) {
            model.addAttribute("items", itemRepository.findAll());
            return "items";
        }

        @PostMapping
        public String saveItem(@ModelAttribute Item item) {
            itemRepository.save(item);
            return "redirect:/items";
        }
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
            if (itemRepository.findAll().isEmpty()) {
                List<Item> items = IntStream.range(1, 11)
                        .boxed()
                        .map(index -> new Item("Item " + index))
                        .toList();

                itemRepository.saveAll(items);
            }
        }
    }
}