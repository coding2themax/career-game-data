package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Estimate;
import com.coding2themax.career.game.careergamedata.service.EstimateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/estimate")
public class EstimateController {

  @Autowired
  private EstimateService estimateService;

  @GetMapping("/all")
  public Flux<Estimate> getAll() {
    return this.estimateService.getAllEstimates();
  }

  @PostMapping
  public Mono<Estimate> createEstimateMono(@RequestBody Mono<Estimate> estimateMono) {
    return estimateMono.flatMap(this.estimateService::saveEstimate);
  }

  @PutMapping("/{id}")
  public Mono<Estimate> putMethodName(@PathVariable String id, @RequestBody Mono<Estimate> estimate) {

    return estimate.flatMap(e -> {
      return estimateService.updateEstimate(id, e);
    }).switchIfEmpty(Mono.just(new Estimate()));
  }

}
