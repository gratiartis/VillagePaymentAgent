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
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.haftrust.verifier.model.enums.converters.EmployeeTypeConverter;
import org.haftrust.verifier.model.enums.converters.VerificationStatusConverter;
import org.haftrust.verifier.validation.constraint.BankAccountNumber;
import org.haftrust.verifier.validation.constraint.BankSortCode;
import org.haftrust.verifier.validation.constraint.Iban;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_bank")
public class Bank implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idbank")
    private Integer id;
    
    @BankAccountNumber
    @Column(name = "accountnumber")
    private String accountNumber;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "address")
    private String address;
    
    @BankSortCode
    @Column(name = "sort_code")
    private String sortcode;
    
    @Iban
    @Column(name = "iban", unique = true)
    private String iban;
    
    @Column(name = "contact_number")
    private String contactNumber;
    
    @Column(name = "verification_status")
    @Convert(converter = VerificationStatusConverter.class)
    private VerificationStatus verificationStatus;
    
    @Column(name = "verification_date")
    private Date verificationDate;
    
    @Column(name = "verification_comment")
    private String verificationComment;
    
    @Column(name = "employee_type")
    @Convert(converter = EmployeeTypeConverter.class)
    private EmployeeType employeeType;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Verifier verifier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    public void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    @Override
    public String toString() {
        return "Bank { id=" + id 
                + ", accountNumber=" + accountNumber 
                + ", bankName=" + bankName 
                + ", address=" + address 
                + ", sortcode=" + sortcode 
                + ", iban=" + iban 
                + ", contactNumber=" + contactNumber 
                + ", verificationStatus=" + verificationStatus 
                + ", verificationDate=" + verificationDate 
                + ", verificationComment=" + verificationComment 
                + ", employeeType=" + employeeType 
                + ", verifier=" + verifier + '}';
    }


}
