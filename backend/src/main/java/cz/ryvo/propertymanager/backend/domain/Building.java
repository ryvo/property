package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table
public class Building extends DomainObject {

  @Embedded
  private Address address = new Address();

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @JoinColumn(name = "buildingId", referencedColumnName = "id", nullable = false)
  @OrderBy("number ASC")
  private List<BuildingUnit> units = new ArrayList<>();
}
