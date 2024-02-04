package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Table(value = "occupation", schema = "ors")
public class Occupation implements Persistable<Integer> {

  @Id
  Integer code;

  private Integer socCode;

  @Column(value = "occupationtext")
  String occupationText;

  @Column(value = "displayLevel")
  Long displayLevel;
  @Column
  String selectable;

  @Transient
  private boolean newOccupation;

  @Override
  @Nullable
  public Integer getId() {
    return this.code;
  }

  @Override
  public boolean isNew() {
    return this.newOccupation || code == null;
  }

  public Occupation setAsNew() {
    this.newOccupation = true;
    return this;
  }
}
