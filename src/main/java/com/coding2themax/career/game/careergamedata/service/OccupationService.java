package com.coding2themax.career.game.careergamedata.service;

import org.springframework.lang.NonNull;

import com.coding2themax.career.game.careergamedata.model.Occupation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OccupationService {

  Flux<Occupation> getAllOccuations();

  Mono<Occupation> saveOccupation(@NonNull Occupation occupation);

  Mono<Occupation> updateOccupation(String occupationID, final Occupation occupation);
}
