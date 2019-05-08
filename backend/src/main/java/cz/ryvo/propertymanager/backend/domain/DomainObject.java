package cz.ryvo.propertymanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
public abstract class DomainObject implements Persistable<Long> {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Override
  @Transient
  public boolean isNew() {
    return null == getId();
  }
}
