package com.coding2themax.career.game.careergamedata.service;

import org.springframework.lang.NonNull;

import com.coding2themax.career.game.careergamedata.model.Estimate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstimateService {

  Flux<Estimate> getAllEstimates();

  Mono<Estimate> saveEstimate(Estimate estimate);

  Mono<Estimate> findByID(@NonNull Integer id);

  Mono<Estimate> updateEstimate(String id, final Estimate estimate);
}
