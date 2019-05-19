package cz.ryvo.propertymanager.backend.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BuildingDTO extends ApiObject {

  @NotBlank
  private String houseNumber;

  private String registryNumber;

  @NotBlank
  private String streetName;

  @NotBlank
  private String townName;

  @NotBlank
  private String postalCode;

  private String countryName;
}
