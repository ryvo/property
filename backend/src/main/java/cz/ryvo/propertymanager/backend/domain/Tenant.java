package cz.ryvo.propertymanager.backend.domain;

import cz.ryvo.propertymanager.backend.Contact;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Tenant extends DomainObject {
  @Column
  private String name;

  @Embedded
  private Contact contact;

  @Embedded
  private Address address;
}
