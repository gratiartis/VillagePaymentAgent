package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VerifierDAO extends JpaRepository<Verifier, Integer> {

    List<Verifier> findByEmail(String email);
    
    List<Verifier> findByEmailAndStatus(String email, EmploymentStatus employmentStatus);
    
    List<Verifier> findByEmailAndPasswordAndStatus(String email, String password, EmploymentStatus employmentStatus);

    @Query("select v from Verifier v where v.address.employeeType = :employeeType")
    List<Verifier> findByEmployeeType(@Param("employeeType") EmployeeType employeeType);
    
    @Query("select v from Verifier v "
            + "where v.status = :employmentStatus "
            + "and v.address.employeeType = :employeeType")
    List<Verifier> findByEmploymentStatusAndEmployeeType(
            @Param("employmentStatus") EmploymentStatus employmentStatus,
            @Param("employeeType") EmployeeType employeeType);
    
    
    @Query("select v from Verifier v "
            + "where v.status = :employmentStatus "
            + "and v.address.employeeType = :employeeType "
            + "and v.address.region = :region")
    List<Verifier> findByRegionAndEmploymentStatusAndEmployeeType(
            @Param("region") Region region,
            @Param("employmentStatus") EmploymentStatus employmentStatus,
            @Param("employeeType") EmployeeType employeeType);

}
