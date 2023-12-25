package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table // optional
public class Product {

  @Id
  private Integer id;
  private String description;
  private Double price;

}