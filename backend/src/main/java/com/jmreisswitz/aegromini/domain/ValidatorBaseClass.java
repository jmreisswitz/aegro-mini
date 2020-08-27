package com.jmreisswitz.aegromini.domain;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorBaseClass {
    public void checkIfValid() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ValidatorBaseClass>> constraintViolations = validator.validate( this );
        if (constraintViolations.isEmpty()) {
            return;
        }
        String constraintViolationsMessage = constraintViolations.iterator().next().getMessage();
        throw new IllegalArgumentException(constraintViolationsMessage);
    }
}
