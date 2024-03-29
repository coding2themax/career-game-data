package com.coding2themax.career.game.careergamedata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.repository.CategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryRepositoryService implements CategoryService {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryRepositoryService.class);

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
  public Mono<Category> findByID(@NonNull Integer id) {
    return this.repository.findById(id);
  }

  // this.repository.save(cat)
  @Override
  public Mono<Category> updateCategory(String categoryID, Category cat) {
    return this.repository.findById(Integer.parseInt(categoryID))
        .flatMap(c -> {
          c.setCategoryText(cat.getCategoryText());
          c.setDisplayLevel(cat.getDisplayLevel());
          c.setSelectable(cat.getSelectable());
          return this.repository.save(c);
        }).switchIfEmpty(
            this.repository.save(cat.setAsNew()));

  }

}
