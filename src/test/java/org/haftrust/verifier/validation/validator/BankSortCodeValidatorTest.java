package org.haftrust.verifier.validation.validator;

import static org.junit.Assert.*;

import javax.validation.Validator;

import org.haftrust.verifier.validation.ValidationConfig;
import org.haftrust.verifier.validation.ValidationTestHelper;
import org.haftrust.verifier.validation.constraint.BankSortCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationConfig.class })
public class BankSortCodeValidatorTest {

    @Autowired
    private Validator validator;
    
    @Test
    public void shouldValidateSortCodes() {
        assertFalse("A null sort code should not be validated.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount(null)), "sortCode", NotEmpty.class));
        
        assertTrue("A sort code can only contain digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("A12345")), "sortCode", BankSortCode.class));
        
        assertTrue("A sort code cannot be less than 6 digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("12345")), "sortCode", BankSortCode.class));
        
        assertTrue("A sort code cannot be more than 6 digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("1234567")), "sortCode", BankSortCode.class));
        
        assertFalse("A valid sort code is accepted.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("123456")), "sortCode", BankSortCode.class));
    }
    
    private class BankAccount {
        @BankSortCode
        private final String sortCode;
        public BankAccount(String sortCode) { this.sortCode = sortCode; }
        public String getSortCode() { return sortCode; }
    }

}
