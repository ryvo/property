package cz.ryvo.propertymanager.backend.validation.validator;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Person;
import cz.ryvo.propertymanager.backend.domain.PersonType;
import cz.ryvo.propertymanager.backend.validation.constraint.ValidPerson;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static cz.ryvo.propertymanager.backend.domain.PersonType.LEGAL_PERSON;
import static cz.ryvo.propertymanager.backend.domain.PersonType.NATURAL_PERSON;

public class ValidPersonValidator implements ConstraintValidator<ValidPerson, PersonDTO> {
  @Override
  public boolean isValid(PersonDTO value, ConstraintValidatorContext context) {
    return (value.getType() == LEGAL_PERSON && value.getCompanyName() != null)
        || (value.getType() == NATURAL_PERSON && value.getLastName() != null);
  }
}