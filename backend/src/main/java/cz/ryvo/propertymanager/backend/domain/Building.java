package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table
public class Building extends DomainObject {

  @ManyToOne(fetch = LAZY, optional = false)
  @JoinColumn(name = "portfolio_id")
  private Portfolio portfolio;

  @Embedded
  private Address address = new Address();

  @OneToMany(mappedBy = "building", cascade = ALL, orphanRemoval = true)
  private List<BuildingUnit> units = new ArrayList<>();
}
