package com.coding2themax.career.game.careergamedata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.coding2themax.career.game.careergamedata.model.Category;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {

}
