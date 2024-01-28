package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.repository.IndustryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IndustryRepositoryService implements IndustryService {

  @Autowired
  private IndustryRepository repository;

  @Override
  public Flux<Industry> getAllIndustries() {

    return repository.findAll();
  }

  @Override
  public Mono<Industry> saveIndustry(@NonNull Industry industry) {

    return repository.save(industry);
  }

  @Override
  public Mono<Industry> updateIndustry(String industryID, Industry industry) {
    return this.repository.findById(Integer.parseInt(industryID))
        .flatMap(i -> {
          i.setIndustryText(industry.getIndustryText());
          i.setDisplayLevel(industry.getDisplayLevel());
          i.setSelectable(industry.getSelectable());
          return this.repository.save(i);
        }).switchIfEmpty(this.repository.save(industry.setAsNew()));
  }

}
