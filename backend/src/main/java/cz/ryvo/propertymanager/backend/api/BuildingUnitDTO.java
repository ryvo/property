package cz.ryvo.propertymanager.backend.api;

import cz.ryvo.propertymanager.backend.domain.BuildingUnitType;
import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuildingUnitDTO {

  @OutputOnly
  private Long id;

  @NotBlank
  private String name;

  @NotNull
  private BuildingUnitType type;

  private Integer area;

  private Integer storey;
}
