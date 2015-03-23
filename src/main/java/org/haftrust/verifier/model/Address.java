package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.haftrust.verifier.model.enums.converters.EmployeeTypeConverter;
import org.haftrust.verifier.model.enums.converters.VerificationStatusConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_address")
public class Address implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idaddress")
    private Integer id;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "village")
    private String village;
    
    @NotEmpty
    @Column(name = "postcode")
    private String postcode;
    
    @Column(name = "town")
    private String town;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "verification_status")
    @Convert(converter = VerificationStatusConverter.class)
    private VerificationStatus verificationStatus;
    
    @Column(name = "verification_date")
    private Date verificationDate;
    
    @Column(name = "verification_comment")
    private String verificationComment; 
    
    @NotNull
    @Column(name = "employee_type")
    @Convert(converter = EmployeeTypeConverter.class)
    private EmployeeType employeeType;
    
    @NotNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Verifier verifier;
    
    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ht_country_idcountry")
    private Country country;
    
    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ht_region_idregion")
    private Region region;
    
    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ht_district_iddistrict")
    private District district;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationComment() {
        return verificationComment;
    }

    public void setVerificationComment(String verificationComment) {
        this.verificationComment = verificationComment;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    public void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Address { id=" + id 
                + ", street=" + street 
                + ", village=" + village 
                + ", postcode=" + postcode 
                + ", town=" + town 
                + ", city=" + city 
                + ", verificationStatus=" + verificationStatus
                + ", verificationDate=" + verificationDate 
                + ", verificationComment=" + verificationComment 
                + ", verifier=" + verifier 
                + ", country=" + country 
                + ", region=" + region 
                + ", district=" + district + '}';
    }
    
}
