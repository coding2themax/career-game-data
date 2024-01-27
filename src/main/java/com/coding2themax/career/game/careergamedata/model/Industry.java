package com.coding2themax.career.game.careergamedata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Table(value = "industry", schema = "ors")
public class Industry implements Persistable<Integer> {

  @Id
  Integer code;

  @Column(value = "industrytext")
  String industryText;
  @Column(value = "displayLevel")
  Long displayLevel;
  @Column
  String selectable;

  @Transient
  private boolean newIndustry;

  @Override
  @Nullable
  public Integer getId() {
    return code;
  }

  @Override
  public boolean isNew() {
    return this.newIndustry || code == null;
  }

  public Industry setAsNew() {
    this.newIndustry = true;
    return this;
  }

}
