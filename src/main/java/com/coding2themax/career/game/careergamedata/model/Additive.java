package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Table(value = "additive", schema = "ors")
public class Additive implements Persistable<Integer> {

  @Id
  Integer code;

  @Column(value = "additiveText")
  String additiveText;
  @Column(value = "displayLevel")
  Long displayLevel;
  @Column
  String selectable;

  @Transient
  private boolean newAdditive;

  @Override
  @Nullable
  public Integer getId() {
    return code;
  }

  @Override
  public boolean isNew() {
    return this.newAdditive || code == null;
  }

  public Additive setAsNew() {
    this.newAdditive = true;
    return this;
  }

}
