package cz.ryvo.propertymanager.backend.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LeaseDTO extends ApiObject {
  @NotNull
  private Long tenantId;

  @NotNull
  private LocalDate startDate;

  @NotNull
  private LocalDate endDate;

  @NotNull
  private BigDecimal monthlyRent;
}
