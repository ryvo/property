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

  @OneToMany(mappedBy = "portfolio", fetch = LAZY, cascade = ALL, orphanRemoval = true)
  private List<Owner> owners = new ArrayList<>();

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @Cascade(SAVE_UPDATE) // TODO How does this work?
  @JoinColumn(name = "portfolioId", referencedColumnName = "id", nullable = false)
  private List<Building> buildings = new ArrayList<>();
}
