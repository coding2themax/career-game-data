package com.coding2themax.career.game.careergamedata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.service.IndustryService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/industry")
public class IndustryController {

  @Autowired
  private IndustryService industryService;

  @GetMapping("/all")
  public Flux<Industry> getAll() {
    return this.industryService.getAllIndustries();
  }
}
