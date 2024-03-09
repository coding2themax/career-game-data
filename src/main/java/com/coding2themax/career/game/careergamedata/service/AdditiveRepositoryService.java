package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Additive;
import com.coding2themax.career.game.careergamedata.repository.AdditiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AdditiveRepositoryService implements AdditiveService {

  @Autowired
  private AdditiveRepository repository;

  @Override
  public Flux<Additive> getAll() {
    return repository.findAll();
  }

  @Override
  public Mono<Additive> saveAdditive(Additive additive) {
    return saveOrUpdate(additive);
  }

  @SuppressWarnings("null")
  private Mono<Additive> saveOrUpdate(Additive additive) {
    return this.repository.findById(additive.getId()).flatMap(a -> {
      a.setAdditiveText(additive.getAdditiveText());
      a.setDisplayLevel(additive.getDisplayLevel());
      a.setSelectable(additive.getSelectable());
      return this.repository.save(a);
    }).switchIfEmpty(
        this.repository.save(additive.setAsNew()));
  }

  @SuppressWarnings("null")
  @Override
  public Mono<Additive> findByID(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  public Mono<Additive> updateAdditive(String id, Additive additive) {
    additive.setCode(Integer.parseInt(id));

    return saveOrUpdate(additive);
  }

}
