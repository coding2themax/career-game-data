package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Occupation;
import com.coding2themax.career.game.careergamedata.service.OccupationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/occupation")
public class OccupationController {

  @Autowired
  private OccupationService occupationService;

  @GetMapping("/all")
  public Flux<Occupation> getAll() {

    return occupationService.getAllOccuations();
  }

  @PostMapping
  public Mono<Occupation> createOccupation(@RequestBody Mono<Occupation> occupation) {
    return occupation.flatMap(this.occupationService::saveOccupation);
  }

  @PutMapping("/{id}")
  public Mono<Occupation> updateOccupation(@PathVariable String id, @RequestBody Mono<Occupation> occupation) {
    return occupation.flatMap(o -> {
      return occupationService.updateOccupation(id, o);
    }).switchIfEmpty(Mono.just(new Occupation()));
  }

}
