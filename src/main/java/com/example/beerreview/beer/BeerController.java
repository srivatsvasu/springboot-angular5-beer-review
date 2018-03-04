package com.example.beerreview.beer;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BeerController {

    private final BeerRepository repository;

    public BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    Collection<Beer> list() {
        return repository.findAll();
    }

    @PostMapping("/beers")
    Beer saveBeer(@RequestBody Beer beer) {
        return repository.save(beer);
    }

    @GetMapping("/good-beers")
    @CrossOrigin(origins = "http://localhost:4200")
    Collection<Map<String, String>> goodBeers() {
        return repository.findAll().stream()
                .filter(this::isGreat)
                .map(b -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("id", b.getId().toString());
                    m.put("name", b.getName());
                    return m;
                }).collect(Collectors.toList());
    }

    private boolean isGreat(Beer beer) {

        return !beer.getName().equals("Budwiser") &&
                !beer.getName().equals("Kingfisher");
    }


}
