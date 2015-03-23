package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(classes = { DbConfig.class })
})
@ActiveProfiles({ "scratch" })
public class BankDaoTest {

    @Autowired
    private VerifierDAO verifierDao;
    
    @Autowired
    private BankDAO bankDao;
        
    /**
     * Initial test ensures that the 'valid' bank is indeed valid. Subsequent
     * tests take that template and modify specific fields to confirm that the
     * constraint annotations have been applied.
     */
    @Test
    public void shouldSaveValidBank() {
        Verifier verifier = createVerifier("Juan", EmploymentStatus.PRE_REGISTERED);
        
        verifier.setBank(validBank(verifier));
        verifier = verifierDao.save(verifier);
        
        List<Bank> banks = bankDao.findAll();
        assertTrue("Should have found some banks.", banks.size() > 0);
        
        Bank foundBank = bankDao.findOne(verifier.getBank().getId());
        assertNotNull("Should find bank by ID.", foundBank);
    }
    
    /**
     * Separate unit tests will be provided for extensive constraint testing,
     * where it is easier to be more thorough. For the purposes of testing a
     * DAO, we just need to validate that if there is a constraint violation (an
     * invalid IBAN in this case), an exception will be thrown and the entity
     * will not be saved.
     */
    @Test(expected = ConstraintViolationException.class)
    public void shouldNotSaveBankWithConstraintViolation() {
        Verifier verifier = createVerifier("John", EmploymentStatus.PRE_REGISTERED);
        
        Bank bank = validBank(verifier);
        bank.setIban("123456789101234567890");
        verifier.setBank(bank);
        verifier = verifierDao.save(verifier);
    }
    
    private Bank validBank(Verifier verifier) {
        Bank bank = new Bank();
        bank.setAccountNumber("12345678");
        bank.setSortcode("123456");
        bank.setVerifier(verifier);
        bank.setEmployeeType(EmployeeType.VERIFIER);
        bank.setVerificationStatus(VerificationStatus.AWAITING);
        bank.setBankName("The bank");
        bank.setIban("SK3211000000002612890189");
        bank.setContactNumber("07979123456");
        return bank;
    }
    
    private Verifier createVerifier(String name, EmploymentStatus employmentStatus) {
        Verifier verifier = new Verifier();
        verifier.setFirstName(name + "_firstName");
        verifier.setLastName(name + "_surname");
        verifier.setEmail(name + "@usrmm.com");
        verifier.setPassword(name + "p4s5woRd");
        verifier.setStatus(EmploymentStatus.PRE_REGISTERED);
        return verifierDao.save(verifier);
    }

}
