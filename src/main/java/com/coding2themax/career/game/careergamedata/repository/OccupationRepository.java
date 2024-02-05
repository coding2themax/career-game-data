package com.coding2themax.career.game.careergamedata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.coding2themax.career.game.careergamedata.model.Occupation;

@Repository
public interface OccupationRepository extends ReactiveCrudRepository<Occupation, Integer> {

}
