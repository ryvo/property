package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

  @Column(nullable = false)
  private String houseNumber; // Cislo orientacni

  @Column
  private String registryNumber; //Cislo popisne

  @Column(nullable = false)
  private String streetName;

  @Column(nullable = false)
  private String townName;

  @Column(nullable = false)
  private String postalCode;

  @Column
  private String countryName;
}
