package com.github.nielsonrocha.herosapi.service;

import com.github.nielsonrocha.herosapi.document.Heroes;
import com.github.nielsonrocha.herosapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(heroesRepository.findAll());
    }

    public Mono<Heroes> findOne(String id) {
        return Mono.justOrEmpty(heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes) {
        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public Mono<Boolean> deleteByHeroId(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
