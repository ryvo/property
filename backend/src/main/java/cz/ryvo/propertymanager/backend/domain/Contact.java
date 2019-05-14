package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Contact {
  @Column
  private String telephone1;
  @Column
  private String telephone2;
  @Column
  private String email;
}
