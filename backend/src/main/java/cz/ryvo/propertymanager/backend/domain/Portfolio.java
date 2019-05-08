package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Getter
@Setter
@Entity
@Table
public class Portfolio extends DomainObject {

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @Cascade(SAVE_UPDATE)
  @JoinColumn(name = "portfolioId", referencedColumnName = "id", nullable = false)
  private List<Building> buildings = new ArrayList<>();
}
