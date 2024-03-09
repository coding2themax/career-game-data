package com.coding2themax.career.game.careergamedata.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.service.CategoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(CategoryController.class)
public class CategoryControllerTest {

  @MockBean
  private CategoryService categoryService;

  @Autowired
  private WebTestClient webClient;

  @SuppressWarnings("null")
  @Test
  void testCreateProduct() {

    Category cat1 = new Category();

    BDDMockito.when(categoryService.saveCategory(cat1)).thenReturn(Mono.just(cat1));

    this.webClient.post().uri("/category").body(BodyInserters.fromProducer(Mono.just(cat1), Category.class))
        .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
  }

  @Test
  void testGetAll() {

    Category cat1 = new Category();
    Category cat2 = new Category();

    Flux<Category> categories = Flux.just(cat1, cat2);

    BDDMockito.when(categoryService.getAllCategories()).thenReturn(categories);

    this.webClient.get().uri("/category/all").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }

  @SuppressWarnings("null")
  @Test
  void testUpdate() {
    Category cat1 = new Category();

    cat1.setCode(1);
    Mono<Category> catMono = Mono.just(cat1);
    BDDMockito.when(categoryService.updateCategory("1", cat1)).thenReturn(catMono);
    this.webClient.put().uri("/category/{id}", "1").accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromProducer(Mono.just(cat1), Category.class)).exchange().expectStatus().isOk();
  }
}
