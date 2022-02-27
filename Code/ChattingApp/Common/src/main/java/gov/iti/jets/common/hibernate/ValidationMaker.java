package gov.iti.jets.common.hibernate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class ValidationMaker {
    private static final ValidationMaker INSTANCE = new ValidationMaker();
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationMaker() {

    }

//    public static ValidationMaker getInstance() {
//        return INSTANCE;
//    }
//
//    public <T> void validate( T dto ) {
//        Set<ConstraintViolation<T>> violations = validator.validate( dto );
//        for (ConstraintViolation<T> violation : violations) {
//             System.out.println(violation.getMessage());
//        }
//
//    }
}
