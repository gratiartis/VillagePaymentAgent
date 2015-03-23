package org.haftrust.verifier.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.haftrust.verifier.validation.constraint.BankSortCode;

/**
 * Validates a bank sort code.
 * 
 * @author Stephen Masters
 */
public class BankSortCodeValidator implements ConstraintValidator<BankSortCode, String> {

    private final Pattern PATTERN = Pattern.compile("[0-9]{6}");
    
    @Override
    public void initialize(BankSortCode constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Don't validate a null value.
        // A separate NotEmpty or NotNull annotation should be used to indicate whether the property is compulsory.
        if (value == null) return true;
        
        return PATTERN.matcher(value).matches();
    }

}
