/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.validation.constraint.BankAccountNumber;
import org.haftrust.verifier.validation.constraint.BankSortCode;
import org.haftrust.verifier.validation.constraint.Iban;
import org.haftrust.verifier.validation.constraint.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miroslav
 */
public class RegisterVerifierBean {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterVerifierBean.class);

    private String target;
    private int page;

    private int idCountry;
    private Country country;
    private int idRegion;
    private Region region;
    private int idDistrict;
    private District district;

    private int idVerifier;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private java.sql.Date sqlDob;
    
    @NotEmpty @Size(max = 45) @Email
    private String email;
    
    private String telephoneNumber;
    
    // TODO Remove max when BCrypt hashing is in place.
    @NotEmpty @Size(min = 6, max = 45) @Password
    private String password;
    
    private String educationType;
    private String educationLevel;
    private Verifier verifier;

    private int idAddress;
    private String street;
    private String village;
    private String postcode;
    private String town;
    private String city;
    private Address address;

    private MultipartFile file;
    private Image image;

    private int idReference1;
    private String reference1Title;
    private String reference1FullName;
    private String reference1OrganisationName;
    private String reference1Designation;
    private String reference1ContactNumber;
    private String reference1Email;
    private String reference1Address;
    private Reference reference1;

    private int idReference2;
    private String reference2Title;
    private String reference2FullName;
    private String reference2OrganisationName;
    private String reference2Designation;
    private String reference2ContactNumber;
    private String reference2Email;
    private String reference2Address;
    private Reference reference2;

    @BankAccountNumber
    private String bankAccountNumber;
    private String bankName;
    private String bankContactNumber;
    private String bankAddress;
    @BankSortCode
    private String bankSortCode;
    @Iban
    private String bankIban;
    private Bank bank;

    private String identityDocumentType;
    private String identityDocumentNumber;
    private java.sql.Date sqlIdentityDocumentIssueDate;
    private String identityDocumentIssueDate;
    private java.sql.Date sqlIdentityDocumentExpiryDate;
    private String identityDocumentExpiryDate;
    private IdentityDocument identityDocument;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Date getSqlIdentityDocumentExpiryDate() {
        return sqlIdentityDocumentExpiryDate;
    }

    public void setSqlIdentityDocumentExpiryDate(Date sqlIdentityDocumentExpiryDate) {
        this.sqlIdentityDocumentExpiryDate = sqlIdentityDocumentExpiryDate;
    }

    public Date getSqlIdentityDocumentIssueDate() {
        return sqlIdentityDocumentIssueDate;
    }

    public void setSqlIdentityDocumentIssueDate(Date sqlIdentityDocumentIssueDate) {

        this.sqlIdentityDocumentIssueDate = sqlIdentityDocumentIssueDate;
    }

    public Date getSqlDob() {
        return sqlDob;
    }

    public void setSqlDob(Date sqlDob) {
        this.sqlDob = sqlDob;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public IdentityDocument getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(IdentityDocument identityDocument) {
        this.identityDocument = identityDocument;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Reference getReference1() {
        return reference1;
    }

    public void setReference1(Reference reference1) {
        this.reference1 = reference1;
    }

    public Reference getReference2() {
        return reference2;
    }

    public void setReference2(Reference reference2) {
        this.reference2 = reference2;
    }

    public String getIdentityDocumentExpiryDate() {
        return identityDocumentExpiryDate;
    }

    public void setIdentityDocumentExpiryDate(String identityDocumentExpiryDate) {
        Calendar cal = Calendar.getInstance();
        int year = 0;
        int month = 0;
        int day = 0;
        String[] strSplit = identityDocumentExpiryDate.split("-");

        try {
            day = Integer.parseInt(strSplit[0]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[2]);
        } catch (NumberFormatException exc) {
            LOG.error("Surely we shouldn't just be catching this exception?", exc);
        }

        cal.set(year, month - 1, day);
        java.util.Date today = cal.getTime();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        this.sqlIdentityDocumentExpiryDate = java.sql.Date.valueOf(dateFmt.format(today));

        this.identityDocumentExpiryDate = identityDocumentExpiryDate;
    }

    public String getIdentityDocumentIssueDate() {
        return identityDocumentIssueDate;
    }

    public void setIdentityDocumentIssueDate(String identityDocumentIssueDate) {
        Calendar cal = Calendar.getInstance();
        int year = 0;
        int month = 0;
        int day = 0;
        String[] strSplit = identityDocumentIssueDate.split("-");

        try {
            day = Integer.parseInt(strSplit[0]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[2]);
        } catch (NumberFormatException exc) {
            LOG.error("Surely we shouldn't just be catching this exception?", exc);
        }

        cal.set(year, month - 1, day);
        java.util.Date today = cal.getTime();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        this.sqlIdentityDocumentIssueDate = java.sql.Date.valueOf(dateFmt.format(today));

        this.identityDocumentIssueDate = identityDocumentIssueDate;
    }

    public String getIdentityDocumentNumber() {
        return identityDocumentNumber;
    }

    public void setIdentityDocumentNumber(String identityDocumentNumber) {
        this.identityDocumentNumber = identityDocumentNumber;
    }

    public String getIdentityDocumentType() {
        return identityDocumentType;
    }

    public void setIdentityDocumentType(String identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankContactNumber() {
        return bankContactNumber;
    }

    public void setBankContactNumber(String bankContactNumber) {
        this.bankContactNumber = bankContactNumber;
    }

    public String getBankIban() {
        return bankIban;
    }

    public void setBankIban(String bankIban) {
        this.bankIban = bankIban;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankSortCode() {
        return bankSortCode;
    }

    public void setBankSortCode(String bankSortCode) {
        this.bankSortCode = bankSortCode;
    }

    public int getIdReference1() {
        return idReference1;
    }

    public void setIdReference1(int idReference1) {
        this.idReference1 = idReference1;
    }

    public int getIdReference2() {
        return idReference2;
    }

    public void setIdReference2(int idReference2) {
        this.idReference2 = idReference2;
    }

    public String getReference1Address() {
        return reference1Address;
    }

    public void setReference1Address(String reference1Address) {
        this.reference1Address = reference1Address;
    }

    public String getReference1ContactNumber() {
        return reference1ContactNumber;
    }

    public void setReference1ContactNumber(String reference1ContactNumber) {
        this.reference1ContactNumber = reference1ContactNumber;
    }

    public String getReference1Designation() {
        return reference1Designation;
    }

    public void setReference1Designation(String reference1Designation) {
        this.reference1Designation = reference1Designation;
    }

    public String getReference1Email() {
        return reference1Email;
    }

    public void setReference1Email(String reference1Email) {
        this.reference1Email = reference1Email;
    }

    public String getReference1FullName() {
        return reference1FullName;
    }

    public void setReference1FullName(String reference1FullName) {
        this.reference1FullName = reference1FullName;
    }

    public String getReference1OrganisationName() {
        return reference1OrganisationName;
    }

    public void setReference1OrganisationName(String reference1OrganisationName) {
        this.reference1OrganisationName = reference1OrganisationName;
    }

    public String getReference1Title() {
        return reference1Title;
    }

    public void setReference1Title(String reference1Title) {
        this.reference1Title = reference1Title;
    }

    public String getReference2Address() {
        return reference2Address;
    }

    public void setReference2Address(String reference2Address) {
        this.reference2Address = reference2Address;
    }

    public String getReference2ContactNumber() {
        return reference2ContactNumber;
    }

    public void setReference2ContactNumber(String reference2ContactNumber) {
        this.reference2ContactNumber = reference2ContactNumber;
    }

    public String getReference2Designation() {
        return reference2Designation;
    }

    public void setReference2Designation(String reference2Designation) {
        this.reference2Designation = reference2Designation;
    }

    public String getReference2Email() {
        return reference2Email;
    }

    public void setReference2Email(String reference2Email) {
        this.reference2Email = reference2Email;
    }

    public String getReference2FullName() {
        return reference2FullName;
    }

    public void setReference2FullName(String reference2FullName) {
        this.reference2FullName = reference2FullName;
    }

    public String getReference2OrganisationName() {
        return reference2OrganisationName;
    }

    public void setReference2OrganisationName(String reference2OrganisationName) {
        this.reference2OrganisationName = reference2OrganisationName;
    }

    public String getReference2Title() {
        return reference2Title;
    }

    public void setReference2Title(String reference2Title) {
        this.reference2Title = reference2Title;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    public void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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
        Calendar cal = Calendar.getInstance();
        int year = 0;
        int month = 0;
        int day = 0;
        String[] strSplit = dob.split("-");

        try {
            day = Integer.parseInt(strSplit[0]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[2]);
        } catch (NumberFormatException exc) {
            LOG.error("Surely we should do more than just catching this exception?", exc);
        }

        cal.set(year, month - 1, day);
        java.util.Date today = cal.getTime();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        this.sqlDob = java.sql.Date.valueOf(dateFmt.format(today));

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIdVerifier() {
        return idVerifier;
    }

    public void setIdVerifier(int idVerifier) {
        this.idVerifier = idVerifier;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
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

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public String toString() {
        return "RegisterVerifierBean{" + "target=" + target + ", page=" + page + ", idCountry=" + idCountry + ", country=" + country + ", idRegion=" + idRegion + ", region=" + region + ", idDistrict=" + idDistrict + ", district=" + district + ", idVerifier=" + idVerifier + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", dob=" + dob + ", sqlDob=" + sqlDob + ", email=" + email + ", telephoneNumber=" + telephoneNumber + ", password=" + password + ", educationType=" + educationType + ", educationLevel=" + educationLevel + ", verifier=" + verifier + ", idAddress=" + idAddress + ", street=" + street + ", village=" + village + ", postcode=" + postcode + ", town=" + town + ", city=" + city + ", address=" + address + ", file=" + file + ", image=" + image + ", idReference1=" + idReference1 + ", reference1Title=" + reference1Title + ", reference1FullName=" + reference1FullName + ", reference1OrganisationName=" + reference1OrganisationName + ", reference1Designation=" + reference1Designation + ", reference1ContactNumber=" + reference1ContactNumber + ", reference1Email=" + reference1Email + ", reference1Address=" + reference1Address + ", reference1=" + reference1 + ", idReference2=" + idReference2 + ", reference2Title=" + reference2Title + ", reference2FullName=" + reference2FullName + ", reference2OrganisationName=" + reference2OrganisationName + ", reference2Designation=" + reference2Designation + ", reference2ContactNumber=" + reference2ContactNumber + ", reference2Email=" + reference2Email + ", reference2Address=" + reference2Address + ", reference2=" + reference2 + ", bankAccountNumber=" + bankAccountNumber + ", bankName=" + bankName + ", bankContactNumber=" + bankContactNumber + ", bankAddress=" + bankAddress + ", bankSortCode=" + bankSortCode + ", bankIban=" + bankIban + ", bank=" + bank + ", identityDocumentType=" + identityDocumentType + ", identityDocumentNumber=" + identityDocumentNumber + ", sqlIdentityDocumentIssueDate=" + sqlIdentityDocumentIssueDate + ", identityDocumentIssueDate=" + identityDocumentIssueDate + ", sqlIdentityDocumentExpiryDate=" + sqlIdentityDocumentExpiryDate + ", identityDocumentExpiryDate=" + identityDocumentExpiryDate + ", identityDocument=" + identityDocument + '}';
    }
    
    
}
