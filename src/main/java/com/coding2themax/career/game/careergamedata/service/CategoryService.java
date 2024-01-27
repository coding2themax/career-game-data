package com.coding2themax.career.game.careergamedata.service;

import org.springframework.lang.NonNull;

import com.coding2themax.career.game.careergamedata.model.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Flux<Category> getAllCategories();

  Mono<Category> saveCategory(Category category);

  Mono<Category> findByID(@NonNull Integer id);

  Mono<Category> updateCategory(String categoryID, final Category catMono);
}
