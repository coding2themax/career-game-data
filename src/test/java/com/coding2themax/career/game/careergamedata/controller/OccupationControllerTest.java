package com.coding2themax.career.game.careergamedata.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.coding2themax.career.game.careergamedata.model.Occupation;
import com.coding2themax.career.game.careergamedata.service.OccupationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(OccupationController.class)
public class OccupationControllerTest {

  @MockBean
  private OccupationService service;

  @Autowired
  private WebTestClient client;

  @Test
  void testCreateOccupation() {
    Occupation occupation = new Occupation();
    occupation.setCode(123);
    occupation.setDisplayLevel(2l);
    occupation.setOccupationText("Test text");

    BDDMockito.when(service.saveOccupation(occupation)).thenReturn(Mono.just(occupation));

    this.client.post().uri("/occupation").body(BodyInserters.fromProducer(Mono.just(occupation), Occupation.class))
        .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }

  @Test
  void testGetAll() {

    Occupation occupationFirst = new Occupation();
    occupationFirst.setCode(123);
    occupationFirst.setDisplayLevel(2l);
    occupationFirst.setOccupationText("Test text first");

    Occupation occupationSecond = new Occupation();
    occupationSecond.setCode(456);
    occupationSecond.setDisplayLevel(3l);
    occupationSecond.setOccupationText("Test text second");

    Flux<Occupation> occupations = Flux.just(occupationFirst, occupationSecond);

    BDDMockito.when(service.getAllOccuations()).thenReturn(occupations);

    this.client.get().uri("/occupation/all").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }

  @Test
  void testUpdateOccupation() {
    Occupation occupation = new Occupation();
    occupation.setCode(123);
    occupation.setDisplayLevel(2l);
    occupation.setOccupationText("Test text");
    Mono<Occupation> occupationMono = Mono.just(occupation);
    BDDMockito.when(service.updateOccupation("123", occupation)).thenReturn(occupationMono);

    this.client.put().uri("/occupation/{id}", "123").accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromProducer(Mono.just(occupation), Occupation.class)).exchange().expectStatus().isOk();

  }
}
