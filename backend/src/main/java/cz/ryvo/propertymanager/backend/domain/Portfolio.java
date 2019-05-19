package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Getter
@Setter
@Entity
@Table
public class Portfolio extends DomainObject {

  @OneToMany(mappedBy = "portfolio", fetch = LAZY, orphanRemoval = true)
  private List<Owner> owners = new ArrayList<>();

  @OneToMany(mappedBy = "portfolio", fetch = LAZY, orphanRemoval = true)
  private List<Tenant> tenants = new ArrayList<>();

  @OneToMany(mappedBy = "portfolio", cascade = ALL, orphanRemoval = true)
  private List<Building> buildings = new ArrayList<>();
}
