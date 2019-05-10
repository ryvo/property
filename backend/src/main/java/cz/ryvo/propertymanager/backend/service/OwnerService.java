package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Owner;
import org.springframework.lang.NonNull;

import java.util.List;

public interface OwnerService {
  List<Owner> listOwners();
  Owner createOwner(@NonNull Owner owner);
  Owner getOwner(long id);
  Owner updateOwner(long id, @NonNull Owner owner);
}
