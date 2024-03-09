package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Table(value = "estimate", schema = "ors")
public class Estimate implements Persistable<Integer> {
  @Id
  Integer code;
  @Column(value = "estimatetext")
  String estimateText;
  @Column(value = "displayLevel")
  Long displayLevel;
  @Column
  String selectable;

  @Transient
  private boolean newEstimate;

  @Override
  @Nullable
  public Integer getId() {

    return code;
  }

  @Override
  public boolean isNew() {

    return this.newEstimate || code == null;
  }

  public Estimate setAsNew() {
    this.newEstimate = true;
    return this;
  }
}
