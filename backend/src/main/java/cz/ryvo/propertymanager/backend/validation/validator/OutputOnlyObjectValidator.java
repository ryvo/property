package cz.ryvo.propertymanager.backend.validation.validator;

import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OutputOnlyObjectValidator implements ConstraintValidator<OutputOnly, Object> {
  @Override public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    return value == null;
  }
}
