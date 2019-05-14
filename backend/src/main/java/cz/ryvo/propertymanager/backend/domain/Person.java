package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends DomainObject {

  @Enumerated(EnumType.STRING)
  @Column
  private PersonType type;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String companyName;

  @Embedded
  private Contact contact = new Contact();

  @Embedded
  private Address address = new Address();
}
