package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.service.IndustryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/industry")
public class IndustryController {

  @Autowired
  private IndustryService industryService;

  @PostMapping
  public Mono<Industry> save(@RequestBody Mono<Industry> industry) {
    return industry.flatMap(this.industryService::saveIndustry);
  }

  @GetMapping("/all")
  public Flux<Industry> getAll() {
    return this.industryService.getAllIndustries();
  }
}
