package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Additive;
import com.coding2themax.career.game.careergamedata.service.AdditiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/additive")
public class AdditiveController {

  @Autowired
  private AdditiveService additiveService;

  @GetMapping("/all")
  public Flux<Additive> getAll() {
    return additiveService.getAll();
  }

  @PostMapping
  public Mono<Additive> createAdditive(@RequestBody Mono<Additive> additive) {
    return additive.flatMap(this.additiveService::saveAdditive);
  }

  @PutMapping("/{id}")
  public Mono<Additive> updateAdditive(@PathVariable String id, @RequestBody Mono<Additive> additive) {
    return additive.flatMap(a -> {
      return additiveService.updateAdditive(id, a);
    }).switchIfEmpty(Mono.just(new Additive()));
  }

}
