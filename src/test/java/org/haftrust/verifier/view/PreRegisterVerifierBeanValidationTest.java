package org.haftrust.verifier.view;

import static org.junit.Assert.*;

import javax.validation.Validator;

import org.haftrust.verifier.validation.ValidationConfig;
import org.haftrust.verifier.validation.ValidationTestHelper;
import org.haftrust.verifier.validation.constraint.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationConfig.class })
public class PreRegisterVerifierBeanValidationTest {

    @Autowired
    private Validator validator;
    
    @Test
    public void shouldValidateEmail() {
        PreRegisterVerifierBean bean = new PreRegisterVerifierBean();
        
        assertTrue("An email address is required.", 
                ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "email", NotEmpty.class));
        
        bean.setEmail("blah");
        assertTrue("Should reject an invalid email.", 
                ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "email", Email.class));
        
        bean.setEmail("someone@somewhere.org");
        assertFalse("Should accept a valid email.", 
                ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "email", NotEmpty.class));
    }
    
    @Test
    public void shouldValidatePassword() {
        PreRegisterVerifierBean bean = new PreRegisterVerifierBean();
        
        assertTrue("A password is required.", 
                ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "password", NotEmpty.class));
        
        bean.setPassword("abc123");
        
        assertFalse(ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "password"));
        
        bean.setPassword("abc12");

        assertTrue(ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "password", Password.class));
        
        bean.setPassword("abcdef");
        
        assertTrue(ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "password", Password.class));
        
        bean.setPassword("123456");
        
        assertTrue(ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "password", Password.class));
    }
    
    @Test
    public void shouldValidateRepassword() {
        PreRegisterVerifierBean bean = new PreRegisterVerifierBean();
        
        assertTrue("A password duplicate is required.", 
                ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "repassword", NotEmpty.class));
        
        bean.setRepassword("abc123");
        
        assertFalse(ValidationTestHelper.hasConstraintViolation(validator.validate(bean), "repassword"));
    }

}
