package com.coding2themax.career.game.careergamedata.service;

import com.coding2themax.career.game.careergamedata.model.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Flux<Category> getAllCategories();

  Mono<Category> saveCategory(Category category);

  Mono<Category> findByID(Integer id);

  Mono<Category> updateProduct(int productId, final Mono<Category> catMono);
}
