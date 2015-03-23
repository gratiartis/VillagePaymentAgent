package org.haftrust.verifier.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.haftrust.verifier.validation.constraint.Password;

/**
 * Validates a bank sort code.
 * 
 * @author Stephen Masters
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final Pattern CONTAINS_DIGITS = Pattern.compile(".*[0-9].*");
    private final Pattern CONTAINS_LETTERS = Pattern.compile(".*[A-Za-z].*");
    
    @Override
    public void initialize(Password constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Don't validate a null value.
        // A separate NotEmpty or NotNull annotation should be used to indicate whether the property is compulsory.
        if (value == null) return true;

        return value.length() >= 6
                && CONTAINS_DIGITS.matcher(value).matches()
                && CONTAINS_LETTERS.matcher(value).matches();
    }

}
