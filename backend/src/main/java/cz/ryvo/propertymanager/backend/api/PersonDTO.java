package cz.ryvo.propertymanager.backend.api;

import cz.ryvo.propertymanager.backend.domain.PersonType;
import cz.ryvo.propertymanager.backend.validation.constraint.ValidPerson;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ValidPerson
@Getter
@Setter
public class PersonDTO extends ApiObject {

  @NotNull
  private PersonType type;

  private String firstName;

  private String lastName;

  private String companyName;

  private String telephone1;

  private String telephone2;

  private String email;

  private String houseNumber;

  private String registryNumber;

  private String streetName;

  private String townName;

  private String postalCode;

  private String countryName;
}
