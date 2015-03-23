package org.haftrust.verifier.validation.validator;

import static org.junit.Assert.*;

import javax.validation.Validator;

import org.haftrust.verifier.validation.ValidationConfig;
import org.haftrust.verifier.validation.ValidationTestHelper;
import org.haftrust.verifier.validation.constraint.Password;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationConfig.class })
public class PasswordValidatorTest {

    @Autowired
    private Validator validator;
    
    @Test
    public void shouldEnforceSixOrMoreCharacters() {
        assertTrue("A password cannot be less than 6 characters.",
                ValidationTestHelper.hasConstraintViolation(
                        validator.validate(new StubUser("abc12")), "password", Password.class));
    }
    
    @Test
    public void shouldEnforceContainsLetters() {
        assertTrue("A password must contain letters.",
                ValidationTestHelper.hasConstraintViolation(
                        validator.validate(new StubUser("012345")), "password", Password.class));
    }
    
    @Test
    public void shouldEnforceContainsDigits() {
        assertTrue("A password must contain digits.",
                ValidationTestHelper.hasConstraintViolation(
                        validator.validate(new StubUser("Abcdef")), "password", Password.class));
    }
    
    @Test
    public void shouldAcceptUppercaseLetters() {
        assertFalse("A valid password with uppercase letters should be accepted.",
                ValidationTestHelper.hasConstraintViolation(
                        validator.validate(new StubUser("ABC123")), "password", Password.class));
    }
       
    @Test
    public void shouldAcceptLowercaseLetters() {
        assertFalse("A valid password with lowercase letters should be accepted.",
                ValidationTestHelper.hasConstraintViolation(
                        validator.validate(new StubUser("abc123")), "password", Password.class));
    }
    
    private class StubUser {
        @Password
        private final String password;
        public StubUser(String password) { this.password = password; }
        public String getSortCode() { return password; }
    }

}
