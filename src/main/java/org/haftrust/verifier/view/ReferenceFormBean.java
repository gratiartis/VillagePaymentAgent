package org.haftrust.verifier.view;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
public class ReferenceFormBean {

    private Integer validatorId;

    private Integer id;
    
    private String title;
    
    @NotEmpty @Size(max = 45)
    private String fullName;
    
    @NotEmpty
    private String organisationName;
    
    @NotEmpty
    private String designation;
    
    @NotEmpty
    private String contactNumber;
    
    @NotEmpty @Size(max = 45) @Email
    private String email;
    
    @NotEmpty
    private String address;
    
    public ReferenceFormBean() {
    }

    public Integer getValidatorId() {
        return validatorId;
    }

    public void setValidatorId(Integer validatorId) {
        this.validatorId = validatorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ReferenceFormBean: { id=" + id 
                + ", validatorId=" + validatorId
                + ", title=" + title 
                + ", fullName=" + fullName 
                + ", organisationName=" + organisationName 
                + ", designation=" + designation
                + ", contactNumber=" + contactNumber
                + ", email=" + email
                + ", address=" + address
                + " }";
    }
    
}
