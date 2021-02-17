package com.github.nielsonrocha.herosapi.controller;

import com.github.nielsonrocha.herosapi.document.Heroes;
import com.github.nielsonrocha.herosapi.service.HeroesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/heroes")
@AllArgsConstructor
public class HeroesController {

    private final HeroesService heroesService;

    @GetMapping
    public Flux<Heroes> getAllItems() {
        return heroesService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> getById(@PathVariable String id) {
        return heroesService.findOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        return heroesService.save(heroes);
    }

    @DeleteMapping("/{id}")
    public Mono<HttpStatus> deleteById(@PathVariable String id) {
        return heroesService.deleteByHeroId(id)
                .thenReturn(HttpStatus.NO_CONTENT);
    }
}
