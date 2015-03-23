package org.haftrust.verifier.validation.validator;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.haftrust.verifier.validation.ValidationConfig;
import org.haftrust.verifier.validation.constraint.Iban;
import org.haftrust.verifier.validation.validator.IbanValidator.Strictness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationConfig.class })
public class IbanValidatorTest {

    public static final String[] strictValidIbans = {
        "BG29FINV915010EUR0IKFF",
        "ES5702170302862100282783",
        "SK3211000000002612890189",
        "AD1200012030200359100100",
    };
    
    public static final String[] lenientValidIbans = {
        "ES23 0217 0099 47",
        "LU36 0029 1524 6005 0000",
        "AT61 1904 3002 3457 3201",
        "be68 5390 0754 7034",
        "hr12 1001 0051 8630 0016 0",
        "cy17 0020 0128 0000 0012 0052 7600"
    };
    
    public static final String[] invalidIbans = {
        "IT72C012574030100000000789",
        "ES050217009945",
        "ES150217002616",
        "ES350217009941",
        "LU360029153460050000",
        "TN591421720710070712 9648"
    };
    
    private IbanValidator ibanValidator = new IbanValidator();
    
    @Autowired
    private Validator validator;
    
    /**
     * It's not part of the public interface, but it's useful to validate that
     * the regex being used for validating the basic structure of the IBAN is
     * valid, in isolation.
     */
    @Test
    public void shouldValidateStructure() {
        assertTrue("Should start with 2 letters, 2 digits and at between 10 and 30 account identifier characters.", 
                ibanValidator.isValidStructure("GB12XXXXXXXXXX"));
        assertFalse("Should start with a valid country ISO code.", 
                ibanValidator.isValidCountry("ZZ12XXXXXXXXXX"));
        assertFalse("Should reject if less than 10 account identifier characters.", 
                ibanValidator.isValidStructure("GB12XXXXXXXXX"));
        assertFalse("Should reject if more than 30 account identifier characters.", 
                ibanValidator.isValidStructure("GB12XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
        assertFalse("Should reject if first 2 chars are not uppercase letters.", 
                ibanValidator.isValidStructure("1212XXXXXXXXXX"));
        assertFalse("Should reject if second 2 chars are not check digits.", 
                ibanValidator.isValidStructure("GBABXXXXXXXXXX"));
    }
    
    @Test
    public void shouldValidate() {
        for (String iban : strictValidIbans) {
            assertTrue(ibanValidator.isValid(iban));
        }
        for (String iban : invalidIbans) {
            assertFalse(ibanValidator.isValid(iban));
        }
        for (String iban : lenientValidIbans) {
            assertFalse("Spaces in the IBAN make it invalid.", ibanValidator.isValid(iban));
        }
    }
    
    @Test
    public void shouldValidateField() {
        for (String iban : strictValidIbans) {
            BankAccount account = new BankAccount(iban);
            Set<ConstraintViolation<BankAccount>> violations = this.validator.validate(account);
            assertTrue("Valid IBANs should be accepted.", violations.isEmpty());
        }
        for (String iban : invalidIbans) {
            BankAccount account = new BankAccount(iban);
            Set<ConstraintViolation<BankAccount>> violations = this.validator.validate(account);
            assertFalse("Invalid IBANs should be rejected.", violations.isEmpty());
        }
        for (String iban : lenientValidIbans) {
            BankAccount account = new BankAccount(iban);
            Set<ConstraintViolation<BankAccount>> violations = this.validator.validate(account);
            assertFalse("Strict validation should reject IBANs with spaces.", violations.isEmpty());
        }
    }
    
    @Test
    public void shouldValidateFieldLeniently() {
        for (String iban : strictValidIbans) {
            LenientBankAccount account = new LenientBankAccount(iban);
            Set<ConstraintViolation<LenientBankAccount>> violations = this.validator.validate(account);
            assertTrue("Lenient validation should accept 'strict' valid IBANs.", violations.isEmpty());
        }
        for (String iban : invalidIbans) {
            LenientBankAccount account = new LenientBankAccount(iban);
            Set<ConstraintViolation<LenientBankAccount>> violations = this.validator.validate(account);
            assertFalse("Lenient validation should reject invalid IBANS.", violations.isEmpty());
        }
        for (String iban : lenientValidIbans) {
            LenientBankAccount account = new LenientBankAccount(iban);
            Set<ConstraintViolation<LenientBankAccount>> violations = this.validator.validate(account);
            assertTrue("When lenient, spaces should not cause rejection.", violations.isEmpty());
        }
    }
    
    private class BankAccount {
        @Iban
        private final String iban;
        public BankAccount(String iban) { this.iban = iban; }
        public String getIban() { return iban; }
    }
    
    private class LenientBankAccount {
        @Iban(strictness = Strictness.LENIENT)
        private final String iban;
        public LenientBankAccount(String iban) { this.iban = iban; }
        public String getIban() { return iban; }
    }

}
