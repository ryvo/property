package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table
public class Owner extends Person {

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "portfolio_id")
  private Portfolio portfolio;

  @OneToMany(mappedBy = "owner", fetch = LAZY)
  private List<BuildingUnit> buildingUnits = new ArrayList<>();
}
