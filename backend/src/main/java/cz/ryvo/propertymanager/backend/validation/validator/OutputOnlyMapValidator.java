package cz.ryvo.propertymanager.backend.validation.validator;

import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class OutputOnlyMapValidator implements ConstraintValidator<OutputOnly, Map<?, ?>> {
  @Override public boolean isValid(Map<?, ?> value, ConstraintValidatorContext context) {
    return value == null || value.isEmpty();
  }
}
