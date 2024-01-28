package com.coding2themax.career.game.careergamedata.service;

import org.springframework.lang.NonNull;

import com.coding2themax.career.game.careergamedata.model.Industry;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IndustryService {
  Flux<Industry> getAllIndustries();

  Mono<Industry> saveIndustry(@NonNull Industry industry);

  Mono<Industry> updateIndustry(String industryID, final Industry industry);

}
