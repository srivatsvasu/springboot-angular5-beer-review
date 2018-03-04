package com.example.beerreview.beer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;
@Component
public class BeerCLR implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerCLR(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Stream.of("Coors Light", "Budwiser", "Kingfisher", "Good Morning", "Very Hazy", "Moon Light", "PBR")
                .forEach(name -> beerRepository.save(new Beer(name)));
        beerRepository.findAll().forEach(System.out::println);
    }
}
