package com.coding2themax.career.game.careergamedata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.repository.CategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

  @Autowired
  private CategoryRepository repository;

  @Override
  public Flux<Category> getAllCategories() {

    return repository.findAll();
  }

  @Override
  public Mono<Category> saveCategory(Category category) {
    LOG.info("saving category {}", category.getCode());

    return this.repository.save(category);

  }

  @Override
  public Mono<Category> findByID(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  public Mono<Category> updateCategory(String categoryID, Mono<Category> catMono) {
    return this.repository.findById(Integer.parseInt(categoryID))
        .flatMap(c -> catMono.map(u -> {
          c.setCategoryText(u.getCategoryText());
          c.setDisplayLevel(u.getDisplayLevel());
          c.setSelectable(u.getSelectable());
          return c;
        }))
        .flatMap(p -> this.repository.save(p));

  }

}
