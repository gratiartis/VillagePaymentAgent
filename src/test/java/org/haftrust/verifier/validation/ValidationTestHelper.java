package org.haftrust.verifier.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidationTestHelper {

    public static boolean hasConstraintViolation(
            Set<ConstraintViolation<Object>> violations,
            String propertyName) {

        for (ConstraintViolation<Object> violation : violations) {
            if (violation.getPropertyPath().toString().equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasConstraintViolation(
            Set<ConstraintViolation<Object>> violations,
            String propertyName, 
            Class<?> constraint) {

        for (ConstraintViolation<Object> violation : violations) {
            if (violation.getPropertyPath().toString().equals(propertyName)
                    && violation.getConstraintDescriptor().getAnnotation().annotationType() == constraint) {
                return true;
            }
        }
        return false;
    }

}
