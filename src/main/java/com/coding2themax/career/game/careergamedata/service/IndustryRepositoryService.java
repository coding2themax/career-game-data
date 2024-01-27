package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.repository.IndustryRepository;

import reactor.core.publisher.Flux;

@Service
public class IndustryRepositoryService implements IndustryService {

  @Autowired
  private IndustryRepository repository;

  @Override
  public Flux<Industry> getAllIndustries() {

    return repository.findAll();
  }

}
