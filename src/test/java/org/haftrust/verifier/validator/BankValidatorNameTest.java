/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.validator;

import org.junit.Test;

/**
 *
 * @author amarinperez
 */
public class BankValidatorNameTest extends BankValidatorTestBase {
    @Test
    public void testBankNameTooLong()
    {
        bean.setBankName("Long text that is going to make validation fail because names cannot be this long.");
        
        validate();
        assertErrorContainingMessage("up to 45 characters");
    }
}
