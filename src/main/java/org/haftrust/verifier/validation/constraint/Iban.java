package org.haftrust.verifier.validation.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static org.haftrust.verifier.validation.validator.IbanValidator.Strictness.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.haftrust.verifier.validation.validator.IbanValidator;
import org.haftrust.verifier.validation.validator.IbanValidator.Strictness;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IbanValidator.class)
public @interface Iban {

    String message() default "{validation.constraints.banking.iban}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Whether the IBAN should be validated in strict manner, or spaces and
     * lower-case characters are permitted.
     * 
     * @return The strictness.
     */
    Strictness strictness() default STRICT;

}
