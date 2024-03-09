package com.coding2themax.career.game.careergamedata.service;

import com.coding2themax.career.game.careergamedata.model.Additive;

import io.micrometer.common.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdditiveService {

  Flux<Additive> getAll();

  Mono<Additive> saveAdditive(Additive additive);

  Mono<Additive> findByID(@NonNull Integer id);

  Mono<Additive> updateAdditive(String id, final Additive additive);

}
