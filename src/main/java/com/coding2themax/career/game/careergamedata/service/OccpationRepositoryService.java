package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Occupation;
import com.coding2themax.career.game.careergamedata.repository.OccupationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OccpationRepositoryService implements OccupationService {

  @Autowired
  private OccupationRepository repository;

  @Override
  public Flux<Occupation> getAllOccuations() {
    return repository.findAll();
  }

  @Override
  public Mono<Occupation> saveOccupation(@NonNull Occupation occupation) {
    return repository.save(occupation);
  }

  @SuppressWarnings("null")
  @Override
  public Mono<Occupation> updateOccupation(String occupationID, Occupation occupation) {
    return this.repository.findById(Integer.parseInt(occupationID))
        .flatMap(i -> {
          i.setOccupationText(occupation.getOccupationText());
          i.setDisplayLevel(occupation.getDisplayLevel());
          i.setSelectable(occupation.getSelectable());
          return this.repository.save(i);
        }).switchIfEmpty(this.repository.save(occupation.setAsNew()));

  }

}
