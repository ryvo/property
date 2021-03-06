package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table
public class BuildingUnit extends DomainObject {

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "building_id")
  private Building building;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @Column(nullable = false)
  private String name;

  @Enumerated(STRING)
  @Column(nullable = false)
  private BuildingUnitType type;

  @Column
  private Integer area;

  @Column
  private Integer storey;
}
