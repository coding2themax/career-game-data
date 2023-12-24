package com.coding2themax.career.game.careergamedata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.coding2themax.career.game.careergamedata.handler.CategoryHandler;

@Configuration
public class CategoryRouter {

  @Bean
  public RouterFunction<ServerResponse> route(CategoryHandler handler) {

    return RouterFunctions.route()
        .GET("/categories", RequestPredicates.accept(MediaType.APPLICATION_JSON),
            handler::getCategories)
        .POST("/category", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::saveCategory)
        .build();

  }

}
