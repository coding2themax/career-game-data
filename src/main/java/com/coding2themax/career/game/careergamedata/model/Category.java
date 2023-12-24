package com.coding2themax.career.game.careergamedata.model;

import java.util.Objects;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Table
public class Category implements Persistable<Long> {

  @Id
  Long code;
  @Column(value = "categorytext")
  String categoryText;
  @Column(value = "displayLevel")
  Long displayLevel;
  @Column
  String selectable;

  @Override
  @Nullable
  public Long getId() {
    return code;
  }

  @Override
  public boolean isNew() {
    boolean result = Objects.isNull(code);
    this.code = result ? new Random().nextLong() : this.code;

    return result;
  }

}
