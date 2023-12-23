package com.coding2themax.career.game.careergamedata.service;

import com.coding2themax.career.game.careergamedata.model.Category;

import reactor.core.publisher.Flux;

public interface CategoryService {

  Flux<Category> getAllCategories();

}
