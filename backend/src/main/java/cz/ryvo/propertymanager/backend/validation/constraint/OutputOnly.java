package cz.ryvo.propertymanager.backend.validation.constraint;

import cz.ryvo.propertymanager.backend.validation.validator.OutputOnlyIterableValidator;
import cz.ryvo.propertymanager.backend.validation.validator.OutputOnlyMapValidator;
import cz.ryvo.propertymanager.backend.validation.validator.OutputOnlyObjectValidator;

import javax.validation.Constraint;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Target({FIELD, TYPE})
@Constraint(validatedBy = {
    OutputOnlyObjectValidator.class,
    OutputOnlyIterableValidator.class,
    OutputOnlyMapValidator.class
})
public @interface OutputOnly {
}
