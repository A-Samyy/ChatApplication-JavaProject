package gov.iti.jets.common.hibernate;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
public class ValidationMaker {
    private static final ValidationMaker INSTANCE = new ValidationMaker();
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationMaker() {
    }
}
