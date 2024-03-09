package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Estimate;
import com.coding2themax.career.game.careergamedata.repository.EstimateRespository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstimateRepositoryService implements EstimateService {

  @Autowired
  private EstimateRespository respository;

  @Override
  public Flux<Estimate> getAllEstimates() {
    return respository.findAll();
  }

  @Override
  public Mono<Estimate> saveEstimate(Estimate estimate) {
    return saveOrUpDate(estimate);
  }

  @Override
  public Mono<Estimate> findByID(@NonNull Integer id) {

    return respository.findById(id);
  }

  @SuppressWarnings("null")
  @Override
  public Mono<Estimate> updateEstimate(String id, Estimate estimate) {

    estimate.setCode(Integer.parseInt(id));
    return saveEstimate(estimate);
  }

  public Mono<Estimate> saveOrUpDate(Estimate estimate) {
    return this.respository.findById(estimate.getId())
        .flatMap(e -> {
          e.setDisplayLevel(estimate.getDisplayLevel());
          e.setEstimateText(estimate.getEstimateText());
          e.setSelectable(estimate.getSelectable());
          return this.respository.save(e);
        }).switchIfEmpty(this.respository.save(estimate.setAsNew()));
  }
}
