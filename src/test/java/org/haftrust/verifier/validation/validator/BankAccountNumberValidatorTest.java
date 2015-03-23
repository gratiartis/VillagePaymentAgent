package org.haftrust.verifier.validation.validator;

import static org.junit.Assert.*;

import javax.validation.Validator;

import org.haftrust.verifier.validation.ValidationConfig;
import org.haftrust.verifier.validation.ValidationTestHelper;
import org.haftrust.verifier.validation.constraint.BankAccountNumber;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationConfig.class })
public class BankAccountNumberValidatorTest {

    @Autowired
    private Validator validator;
    
    @Test
    public void shouldValidateAccountNumbers() {
        assertFalse("A null account number should not be validated.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount(null)), "accountNumber", NotEmpty.class));
        
        assertTrue("An account number can only contain digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("A12345")), "accountNumber", BankAccountNumber.class));
        
        assertTrue("An account number cannot be less than 7 digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("123456")), "accountNumber", BankAccountNumber.class));
        
        assertTrue("An account number cannot be more than 10 digits.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("12345678901")), "accountNumber", BankAccountNumber.class));
        
        assertFalse("A 7-digit valid account number is accepted.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("1234567")), "accountNumber", BankAccountNumber.class));
        
        assertFalse("A 10-digit valid account number is accepted.",
                ValidationTestHelper.hasConstraintViolation(validator.validate(new BankAccount("1234567890")), "accountNumber", BankAccountNumber.class));
    }
    
    private class BankAccount {
        @BankAccountNumber
        private final String accountNumber;
        public BankAccount(String accountNumber) { this.accountNumber = accountNumber; }
        public String getAccountNumber() { return accountNumber; }
    }

}
