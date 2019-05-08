package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table
public class BuildingUnit extends DomainObject {

  @Column(nullable = false)
  private String number;

  @Enumerated(STRING)
  @Column(nullable = false)
  private BuildingUnitType type;

  @Column
  private Integer area;

  @Column
  private Integer storey;
}
