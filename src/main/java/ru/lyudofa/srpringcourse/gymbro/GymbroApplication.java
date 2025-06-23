package ru.lyudofa.srpringcourse.gymbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GymbroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymbroApplication.class, args);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 100));
        }
        List <String> l = list.stream().filter((x) -> x % 2 == 0).map(x -> "num: " + x).filter((s) -> s.endsWith("0")).map(s -> s + "!").toList();

    }

}
