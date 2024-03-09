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
import com.coding2themax.career.game.careergamedata.model.Estimate;
import com.coding2themax.career.game.careergamedata.service.EstimateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(EstimateController.class)
public class EstimateControllerTest {

  @MockBean
  private EstimateService service;

  @Autowired
  private WebTestClient webClient;

  @Test
  void testCreateEstimateMono() {

    Estimate e1 = new Estimate();
    BDDMockito.when(service.saveEstimate(e1)).thenReturn(Mono.just(e1));

    this.webClient.post().uri("/estimate").body(BodyInserters.fromProducer(Mono.just(e1), Estimate.class))
        .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
  }

  @Test
  void testGetAll() {

    Estimate e1 = new Estimate();
    Estimate e2 = new Estimate();

    Flux<Estimate> estimates = Flux.just(e1, e2);

    BDDMockito.when(service.getAllEstimates()).thenReturn(estimates);

    this.webClient.get().uri("/estimate/all").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }

  @Test
  void testPutMethodName() {

    Estimate e1 = new Estimate();
    e1.setCode(1);
    Mono<Estimate> eMono = Mono.just(e1);
    BDDMockito.when(service.updateEstimate("1", e1)).thenReturn(eMono);
    this.webClient.put().uri("/estimate/{id}", "1").accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromProducer(Mono.just(e1), Category.class)).exchange().expectStatus().isOk();
  }
}
