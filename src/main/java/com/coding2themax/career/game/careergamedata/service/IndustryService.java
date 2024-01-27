package com.coding2themax.career.game.careergamedata.service;

import com.coding2themax.career.game.careergamedata.model.Industry;

import reactor.core.publisher.Flux;

public interface IndustryService {
  Flux<Industry> getAllIndustries();

}
