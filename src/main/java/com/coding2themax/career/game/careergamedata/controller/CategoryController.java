package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.service.CategoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/all")
  public Flux<Category> getAll() {
    return this.categoryService.getAllCategories();
  }

  @PostMapping
  public Mono<Category> createCategoryMono(@RequestBody Mono<Category> categoryMono) {
    return categoryMono.flatMap(this.categoryService::saveCategory);
  }

  @PutMapping("/{id}")
  public Mono<Category> updateCategory(@PathVariable String id, @RequestBody Mono<Category> categoryMono) {

    return categoryMono.flatMap(c -> {
      return categoryService.updateCategory(id, c);
    }).switchIfEmpty(Mono.just(new Category()));

  }

}
