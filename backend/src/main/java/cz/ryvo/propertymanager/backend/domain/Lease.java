package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table
public class Lease extends DomainObject {
  @Column(nullable = false)
  private LocalDate startDate;

  @Column
  private LocalDate endDate;

  @Column(nullable = false)
  private BigDecimal monthlyRent;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "building_unit_id", nullable = false)
  private BuildingUnit buildingUnit;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "tenant_id", nullable = false)
  private Tenant tenant;
}
