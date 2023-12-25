package com.coding2themax.career.game.careergamedata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.coding2themax.career.game.careergamedata.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

}
