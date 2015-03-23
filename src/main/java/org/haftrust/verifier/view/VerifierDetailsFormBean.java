package org.haftrust.verifier.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public class VerifierDetailsFormBean {

    private Integer verifierId;
    @NotEmpty @Size(max = 45)
    private String firstName;
    @Size(max = 45)
    private String middleName;
    @NotEmpty @Size(max = 45)
    private String lastName;
    private String gender;
    @NotNull @Past
    private String dob;
    @NotEmpty @Size(max = 25)
    private String telephoneNumber;
    private String educationType;
    private String educationLevel;
    // TODO Create a constraint to validate that a file is an image.
    private MultipartFile file;
    @NotNull
    private Image image;

    private String street;
    private String village;
    @NotEmpty @Size(max = 10)
    private String postcode;
    private String town;
    private String city;
    private District district;
    private Region region;
    private Country country;

    public VerifierDetailsFormBean() {
    }
    
    public VerifierDetailsFormBean(Verifier verifier) {
        this.verifierId = verifier.getId();
        this.firstName = verifier.getFirstName();
        this.lastName = verifier.getLastName();
        this.telephoneNumber = verifier.getTelephoneNumber();
        
        this.gender = verifier.getGender().getKey();
        this.educationLevel = verifier.getEducationLevel().getKey();
        this.educationType = verifier.getEducationType().getKey();
        
        this.image = verifier.getImage();
        
        if (verifier.getDob() != null) {
            LocalDate date = verifier.getDob();
            this.dob = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        
        if (verifier.getAddress() != null) {
            this.street = verifier.getAddress().getStreet();
            this.village = verifier.getAddress().getVillage();
            this.town = verifier.getAddress().getTown();
            this.city = verifier.getAddress().getCity();
            this.postcode = verifier.getAddress().getPostcode();
            this.district = verifier.getAddress().getDistrict();
            this.region = verifier.getAddress().getRegion();
            this.country = verifier.getAddress().getCountry();
        }
    }

    public Integer getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(Integer verifierId) {
        this.verifierId = verifierId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "VerifierDetailsFormBean: { verifierId=" + verifierId + " }";
    }

}
