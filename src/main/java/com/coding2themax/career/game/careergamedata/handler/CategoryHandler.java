package com.coding2themax.career.game.careergamedata.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.coding2themax.career.game.careergamedata.service.CategoryService;

import reactor.core.publisher.Mono;

@Component
public class CategoryHandler {

  CategoryService service;

  public Mono<ServerResponse> getCategories(ServerRequest request) {

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(service.getAllCategories()));

  }
}
