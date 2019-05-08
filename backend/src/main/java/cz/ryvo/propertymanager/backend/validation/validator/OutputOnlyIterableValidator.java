package cz.ryvo.propertymanager.backend.validation.validator;

import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OutputOnlyIterableValidator implements ConstraintValidator<OutputOnly, Iterable<?>> {
  @Override public boolean isValid(Iterable<?> value, ConstraintValidatorContext context) {
    return value == null || !value.iterator().hasNext();
  }
}
