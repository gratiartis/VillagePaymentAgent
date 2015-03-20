package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
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
public class VerifierDaoTest {

    private static final LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    
    @Autowired
    private VerifierDAO verifierDao;
    @Autowired
    private AddressDAO addressDao;
    @Autowired
    private DistrictDAO districtDao;

    @Test
    public void shouldSaveAndFindEntities() {
        Verifier susan = createVerifier("VerifierTestSusan", EmploymentStatus.PRE_REGISTERED);
        
        Verifier savedEntity = verifierDao.findOne(susan.getId());
        
        assertNotNull("Should have been assigned an ID, when saved.", savedEntity.getId());
        
        Verifier foundEntity = verifierDao.findOne(savedEntity.getId());
        assertNotNull("Should have found verifier by ID.", savedEntity);
        assertEquals("ID of found entity should be same as that saved.", 
                savedEntity.getId(), foundEntity.getId());
    }

    /** 
     * Throws exception if query is invalid.
     * TODO Set up verifiers with addresses, so that we can validate real results. 
     */
    @Test
    public void shouldFindByEmployeeType() {
        Verifier jean = create("Jean", EmployeeType.VERIFIER, EmploymentStatus.PRE_REGISTERED);
        Verifier eponine = create("Eponine", EmployeeType.VERIFIER, EmploymentStatus.REGISTERED);
        Verifier cosette = create("Cosette", EmployeeType.FIELD_OPERATIVE_MANAGER, EmploymentStatus.REGISTERED);
        Verifier enjolras = create("Enjolras", EmployeeType.FARMER, EmploymentStatus.VERIFIED);
        
        List<Verifier> allEmployees = verifierDao.findAll();
        List<Verifier> verifiers = verifierDao.findByEmployeeType(EmployeeType.VERIFIER);
        List<Verifier> managers = verifierDao.findByEmployeeType(EmployeeType.FIELD_OPERATIVE_MANAGER);
        List<Verifier> farmers = verifierDao.findByEmployeeType(EmployeeType.FARMER);
        
        assertTrue("Jean should be an employee.", ids(allEmployees).contains(jean.getId()));
        assertTrue("Eponine should be an employee.", ids(allEmployees).contains(eponine.getId()));
        assertTrue("Cosette should be an employee.", ids(allEmployees).contains(cosette.getId()));
        assertTrue("Enjolras should be an employee.", ids(allEmployees).contains(enjolras.getId()));
        
        assertTrue("Jean should be a verifier.", ids(verifiers).contains(jean.getId()));
        assertFalse("Cosette should not be a verifier.", ids(verifiers).contains(cosette.getId()));
        
        assertTrue("Cosette should be a manager.", ids(managers).contains(cosette.getId()));
        assertFalse("Jean should not be a manager", ids(managers).contains(jean.getId()));

        // Search for *registered* verifiers. 
        
        List<Verifier> registeredVerifiers = verifierDao.findByEmploymentStatusAndEmployeeType(
                EmploymentStatus.REGISTERED, EmployeeType.VERIFIER);
        
        assertFalse("Jean is not yet registered.", 
                ids(registeredVerifiers).contains(jean.getId()));
        assertTrue("Eponine should be a registered verifier.", 
                ids(registeredVerifiers).contains(eponine.getId()));
        
        // Search for *registered* verifiers within a specific region. 
        
        List<Verifier> registeredVerifiersInDistrict1Region = verifierDao
                .findByRegionAndEmploymentStatusAndEmployeeType(
                        districtDao.findOne(1).getRegion(),
                        EmploymentStatus.REGISTERED, EmployeeType.VERIFIER);
        
        System.out.println("\n\nRegistered verifiers in district 1 region: " 
                + registeredVerifiersInDistrict1Region + "\n\n");
        
        assertTrue("Eponine is registered.", 
                ids(registeredVerifiersInDistrict1Region).contains(eponine.getId()));
        
        List<Verifier> registeredVerifiersInDistrict5Region = verifierDao
                .findByRegionAndEmploymentStatusAndEmployeeType(
                        districtDao.findOne(5).getRegion(),
                        EmploymentStatus.REGISTERED, EmployeeType.VERIFIER);
        
        assertFalse("Eponine is registered in a different region.", 
                ids(registeredVerifiersInDistrict5Region).contains(eponine.getId()));
        
    }
    
    private Verifier create(String name, 
            EmployeeType employeeType,
            EmploymentStatus employmentStatus) {
        
        Verifier verifier = createVerifier(name, employmentStatus);
        Address address = createAddress(verifier, employeeType);
        
        return verifier;
    }
    
    private Verifier createVerifier(String name, EmploymentStatus employmentStatus) {
        
        Verifier verifier = new Verifier();
        verifier.setFirstName(name + "_firstName");
        verifier.setLastName(name + "_surname");
        verifier.setEmail(name + "@usrmm.com");
        verifier.setPassword(name + "p4s5woRd");
        verifier.setStatus(employmentStatus);
        verifier.setStatusDate(java.sql.Date.valueOf(today));
        
        verifier = verifierDao.save(verifier);
        
        return verifier;
    }
    
    private Address createAddress(Verifier verifier, EmployeeType employeeType) {
        
        Address address = new Address();
        address.setEmployeeType(employeeType);
        address.setVerifier(verifier);
        address.setDistrict(districtDao.findOne(1));
        address.setRegion(address.getDistrict().getRegion());
        address.setCountry(address.getRegion().getCountry());
        
        verifier.setAddress(address);
        verifier = verifierDao.save(verifier);
        
        return address;
    }
    
    private List<Integer> ids(List<Verifier> verifiers) {
        return verifiers.stream().map(v -> v.getId()).collect(Collectors.toList());
    }

}
