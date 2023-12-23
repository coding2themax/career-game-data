package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Category {

  @Id
  Long code;
  String categoryText;
  Integer displayLevel;
  String selectable;
  Integer sortSequence;

}
