package org.haftrust.verifier.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.EducationLevel;
import org.haftrust.verifier.model.enums.EducationType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.haftrust.verifier.model.enums.converters.EducationLevelConverter;
import org.haftrust.verifier.model.enums.converters.EducationTypeConverter;
import org.haftrust.verifier.model.enums.converters.EmploymentStatusConverter;
import org.haftrust.verifier.model.enums.converters.VerificationStatusConverter;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "HT_VERIFIER")
public class Verifier implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idverifier")
    private Integer id;
    
    @Column(name = "first_name", length = 45)
    private String firstName;
    
    @Column(name = "middle_name", length = 45)
    private String middleName;
    
    @Column(name = "last_name", length = 45)
    private String lastName;
    
    @Column(name = "gender", length = 1)
    private String gender;
    
    @Column(name = "dob")
    private Date dob;
    
    @Column(name = "email", length = 45, nullable = false)
    private String email;
    
    @Column(name = "telephone_number", length = 25)
    private String telephoneNumber;
    
    @Column(name = "password", length = 45, nullable = false)
    private String password;
    
    @Column(name = "education_type", length = 45)
    @Convert(converter = EducationTypeConverter.class)
    private EducationType educationType;
    
    @Column(name = "education_level", length = 45)
    @Convert(converter = EducationLevelConverter.class)
    private EducationLevel educationLevel;
    
    @Column(name = "status", length = 45, nullable = false)
    @Convert(converter = EmploymentStatusConverter.class)
    private EmploymentStatus status;
    
    @Column(name = "status_date")
    private Date statusDate;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "ht_vacancy_idvacancy")
    private int vacancyId;
    
    @Column(name = "verification_status", length = 45)
    @Convert(converter = VerificationStatusConverter.class)
    private VerificationStatus verificationStatus;
    
    @Column(name = "verification_date")
    private Date verificationDate;
    
    @Column(name = "verification_comment", length = 100)
    private String verificationComment;
    
    @Column(name = "ht_device_imei")
    private Device mobileDevice;
    
    @ManyToOne
    private Image image;
    
    @OneToOne(mappedBy = "verifier", cascade = CascadeType.ALL)
    private Address address;
    
    @OneToOne(mappedBy = "verifier", cascade = CascadeType.ALL)
    private IdentityDocument identity;
    
    @OneToOne(mappedBy = "verifier", cascade = CascadeType.ALL)
    private Bank bank;
    
    @OneToMany
    private List<Reference> references;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
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

    public Device getMobileDevice() {
        return mobileDevice;
    }

    public void setMobileDevice(Device mobileDevice) {
        this.mobileDevice = mobileDevice;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IdentityDocument getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityDocument identity) {
        this.identity = identity;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Reference> getReferences() {
        if (references == null) {
            references = new ArrayList<>();
        }
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }
    
    public Reference getReference1() {
        return getReferences().size() > 0 ? getReferences().get(0) : null;
    }
    
    public Reference getReference2() {
        return getReferences().size() > 1 ? getReferences().get(1) : null;
    }

    @Override
    public String toString() {
        return "Verifier { id=" + id 
                + ", firstName=" + firstName 
                + ", middleName=" + middleName
                + ", lastName=" + lastName 
                + ", gender=" + gender 
                + ", dob=" + dob 
                + ", email=" + email 
                + ", telephoneNumber=" + telephoneNumber 
                + ", password=" + password 
                + ", educationType=" + educationType 
                + ", educationLevel=" + educationLevel 
                + ", status=" + status 
                + ", statusDate=" + statusDate 
                + ", startDate=" + startDate 
                + ", vacancyId=" + vacancyId 
                + ", verificationStatus=" + verificationStatus 
                + ", verificationDate=" + verificationDate 
                + ", verificationComment=" + verificationComment 
                + ", mobileDevice=" + mobileDevice 
                + ", image=" + image + '}';
    }
    
}
