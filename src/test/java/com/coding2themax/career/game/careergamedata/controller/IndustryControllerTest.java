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
import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.service.IndustryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(IndustryController.class)
public class IndustryControllerTest {

  @MockBean
  private IndustryService industryService;
  @Autowired
  private WebTestClient webClient;

  @Test
  void testAdd() {

    Industry industry = new Industry();
    industry.setCode(123);
    industry.setDisplayLevel(1l);
    industry.setIndustryText("Test");

    BDDMockito.when(industryService.saveIndustry(industry)).thenReturn(Mono.just(industry));

    this.webClient.post().uri("/industry").body(BodyInserters.fromProducer(Mono.just(industry), Industry.class))
        .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
  }

  @Test
  void testGetAll() {

    Industry industry1 = new Industry();
    Industry industry2 = new Industry();

    Flux<Industry> industries = Flux.just(industry1, industry2);

    BDDMockito.when(industryService.getAllIndustries()).thenReturn(industries);

    this.webClient.get().uri("/industry/all").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

  }
}
