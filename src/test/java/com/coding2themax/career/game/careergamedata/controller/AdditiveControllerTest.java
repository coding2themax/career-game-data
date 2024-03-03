package com.coding2themax.career.game.careergamedata.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.coding2themax.career.game.careergamedata.model.Additive;
import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.service.AdditiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(AdditiveController.class)
public class AdditiveControllerTest {

  @MockBean
  private AdditiveService service;
  @Autowired
  private WebTestClient client;

  @Test
  void testCreateAdditive() {
    Additive additive = new Additive();
    additive.setCode(123);
    additive.setDisplayLevel(2l);
    additive.setAdditiveText("additive text");

    BDDMockito.when(service.saveAdditive(additive)).thenReturn(Mono.just(additive));

    this.client.post().uri("/additive").body(BodyInserters.fromProducer(Mono.just(additive), Additive.class))
        .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
  }

  @Test
  void testGetAll() {
    Additive additive1 = new Additive();
    Additive additive2 = new Additive();

    Flux<Additive> additives = Flux.just(additive1, additive2);

    BDDMockito.when(service.getAll()).thenReturn(additives);
    this.client.get().uri("/additive/all").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }

  @Test
  void testUpdateAdditive() {
    Additive additive = new Additive();
    additive.setCode(123);
    additive.setDisplayLevel(2l);
    additive.setAdditiveText("additive text");

    Mono<Additive> additiveMono = Mono.just(additive);

    BDDMockito.when(service.updateAdditive("123", additive)).thenReturn(additiveMono);
    this.client.put().uri("/additive/{id}", "123").accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromProducer(Mono.just(additive), Additive.class)).exchange().expectStatus().isOk();

  }
}
