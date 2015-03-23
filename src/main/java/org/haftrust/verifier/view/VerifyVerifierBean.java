/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.Fom;
import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Interview;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.validation.constraint.BankAccountNumber;
import org.haftrust.verifier.validation.constraint.BankSortCode;
import org.haftrust.verifier.validation.constraint.Iban;

/**
 *
 * @author Miroslav
 */
public class VerifyVerifierBean {

    private String target;

    private int idCountry;
    private Country country;
    private int idRegion;
    private Region region;
    private List<Verifier> verifierList = new ArrayList<>();
    private List<VBean> vBean = new ArrayList<>();
    private int idVerifier;
    private List<Fom> fomList = new ArrayList<>();
    private List<FomBean> fBean = new ArrayList<>();
    private int idFom;

    private Fom filedOperativeManager = new Fom();
    private Interview interview = new Interview();

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private String email;
    private String telephoneNumber;
    private String password;
    private String educationType;
    private String educationLevel;
    private String verifierVerificationStatus;
    private String verifierVerificationComment;
    //private Verifier verifier;

    private int idAddress;
    private String street;
    private String village;
    private String postcode;
    private String town;
    private String city;
    private String district;
    private String addressRegion;
    private String addressCountry;
    private Address address;
    private String addressVerificationStatus;
    private String addressVerificationComment;

    BufferedImage fileBi;
    private byte file[];
    private String fileVerificationStatus;
    private String fileVerificationComment;
    private Image image;

    private int idReference1;
    private String reference1Title;
    private String reference1FullName;
    private String reference1OrganisationName;
    private String reference1Designation;
    private String reference1ContactNumber;
    private String reference1Email;
    private String reference1Address;
    private String reference1VerificationStatus;
    private String reference1VerificationComment;
    //private Reference reference1;

    private int idReference2;
    private String reference2Title;
    private String reference2FullName;
    private String reference2OrganisationName;
    private String reference2Designation;
    private String reference2ContactNumber;
    private String reference2Email;
    private String reference2Address;
    private String reference2VerificationStatus;
    private String reference2VerificationComment;
    //private Reference reference2;

    @BankAccountNumber
    private String bankAccountNumber;
    private String bankName;
    private String bankContactNumber;
    private String bankAddress;
    @BankSortCode
    private String bankSortCode;
    @Iban
    private String bankIban;
    private String bankVerificationStatus;
    private String bankVerificationComment;
    //private Bank bank;

    private String identityDocumentType;
    private String identityDocumentNumber;
    private String identityDocumentIssueDate;
    private String identityDocumentExpiryDate;
    private String identityDocumentVerificationStatus;
    private String identityDocumentVerificationComment;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BufferedImage getFileBi() {
        return fileBi;
    }

    public void setFileBi(BufferedImage fileBi) {
        this.fileBi = fileBi;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public Fom getFiledOperativeManager() {
        return filedOperativeManager;
    }

    public void setFiledOperativeManager(Fom filedOperativeManager) {
        this.filedOperativeManager = filedOperativeManager;
    }

    public List<FomBean> getfBean() {
        return fBean;
    }

    public void setfBean(List<Fom> f) {
        //this.fBean.clear();
        List<FomBean> fbl = new ArrayList<>();
        for (Fom f1 : f) {
            String s;
            s = f1.getFirstName() + " " + f1.getLastName();
            FomBean fb = new FomBean();
            fb.setF(f1);
            fb.setLabel(s);
            fb.setId(f1.getId());
            fbl.add(fb);
        }

        this.fBean = fbl;
    }

    public List<Fom> getFomList() {
        return fomList;
    }

    public void setFomList(List<Fom> fomList) {
        //this.fomList.clear();
        this.fomList = fomList;
    }

    public int getIdFom() {
        return idFom;
    }

    public void setIdFom(int idFom) {
        this.idFom = idFom;
    }

    public String getFileVerificationComment() {
        return fileVerificationComment;
    }

    public void setFileVerificationComment(String fileVerificationComment) {
        this.fileVerificationComment = fileVerificationComment;
    }

    public String getFileVerificationStatus() {
        return fileVerificationStatus;
    }

    public void setFileVerificationStatus(String fileVerificationStatus) {
        this.fileVerificationStatus = fileVerificationStatus;
    }

    public String getAddressVerificationComment() {
        return addressVerificationComment;
    }

    public void setAddressVerificationComment(String addressVerificationComment) {
        this.addressVerificationComment = addressVerificationComment;
    }

    public String getAddressVerificationStatus() {
        return addressVerificationStatus;
    }

    public void setAddressVerificationStatus(String addressVerificationStatus) {
        this.addressVerificationStatus = addressVerificationStatus;
    }

    public String getBankVerificationComment() {
        return bankVerificationComment;
    }

    public void setBankVerificationComment(String bankVerificationComment) {
        this.bankVerificationComment = bankVerificationComment;
    }

    public String getBankVerificationStatus() {
        return bankVerificationStatus;
    }

    public void setBankVerificationStatus(String bankVerificationStatus) {
        this.bankVerificationStatus = bankVerificationStatus;
    }

    public String getIdentityDocumentVerificationComment() {
        return identityDocumentVerificationComment;
    }

    public void setIdentityDocumentVerificationComment(String identityDocumentVerificationComment) {
        this.identityDocumentVerificationComment = identityDocumentVerificationComment;
    }

    public String getIdentityDocumentVerificationStatus() {
        return identityDocumentVerificationStatus;
    }

    public void setIdentityDocumentVerificationStatus(String identityDocumentVerificationStatus) {
        this.identityDocumentVerificationStatus = identityDocumentVerificationStatus;
    }

    public String getReference1VerificationComment() {
        return reference1VerificationComment;
    }

    public void setReference1VerificationComment(String reference1VerificationComment) {
        this.reference1VerificationComment = reference1VerificationComment;
    }

    public String getReference1VerificationStatus() {
        return reference1VerificationStatus;
    }

    public void setReference1VerificationStatus(String reference1VerificationStatus) {
        this.reference1VerificationStatus = reference1VerificationStatus;
    }

    public String getReference2VerificationComment() {
        return reference2VerificationComment;
    }

    public void setReference2VerificationComment(String reference2VerificationComment) {
        this.reference2VerificationComment = reference2VerificationComment;
    }

    public String getReference2VerificationStatus() {
        return reference2VerificationStatus;
    }

    public void setReference2VerificationStatus(String reference2VerificationStatus) {
        this.reference2VerificationStatus = reference2VerificationStatus;
    }

    public String getVerifierVerificationComment() {
        return verifierVerificationComment;
    }

    public void setVerifierVerificationComment(String verifierVerificationComment) {
        this.verifierVerificationComment = verifierVerificationComment;
    }

    public String getVerifierVerificationStatus() {
        return verifierVerificationStatus;
    }

    public void setVerifierVerificationStatus(String verifierVerificationStatus) {
        this.verifierVerificationStatus = verifierVerificationStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
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

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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

    public String getIdentityDocumentExpiryDate() {
        return identityDocumentExpiryDate;
    }

    public void setIdentityDocumentExpiryDate(String identityDocumentExpiryDate) {
        this.identityDocumentExpiryDate = identityDocumentExpiryDate;
    }

    public String getIdentityDocumentIssueDate() {
        return identityDocumentIssueDate;
    }

    public void setIdentityDocumentIssueDate(String identityDocumentIssueDate) {
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public List<VBean> getvBean() {
        return vBean;
    }

    public void setvBean(List<Verifier> v) {
        //this.vBean.clear();
        List<VBean> vbl = new ArrayList<VBean>();
        for (int i = 0; i < v.size(); i++) {
            String s;

            String date;
            date = v.get(i).getDob().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            String[] strSplit = date.split("-");
            day = Integer.parseInt(strSplit[2]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            s = v.get(i).getFirstName() + " " + v.get(i).getLastName() + " " + date;
            VBean vb = new VBean();
            vb.setVer(v.get(i));
            vb.setLabel(s);
            vb.setId(v.get(i).getId());
            vbl.add(vb);
        }

        this.vBean = vbl;
    }

    public int getIdVerifier() {
        return idVerifier;
    }

    public void setIdVerifier(int idVerifier) {
        this.idVerifier = idVerifier;
    }

    public List<Verifier> getVerifierList() {
        return verifierList;
    }

    public void setVerifierList(List<Verifier> verifierList) {
        //this.verifierList.clear();
        this.verifierList = verifierList;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    class FomBean {

        Fom f = new Fom();
        String label;
        int id;

        public Fom getF() {
            return f;
        }

        public void setF(Fom f) {
            this.f = f;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    // FIXME Is this a Dupe? -BE
    static class VBean {

        Verifier ver = new Verifier();
        String label;
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Verifier getVer() {
            return ver;
        }

        public void setVer(Verifier ver) {
            this.ver = ver;
        }
    }

    @Override
    public String toString() {
        return "VerifyVerifierBean{" + "target=" + target + ", idCountry=" + idCountry + ", country=" + country + ", idRegion=" + idRegion + ", region=" + region + ", verifierList=" + verifierList + ", vBean=" + vBean + ", idVerifier=" + idVerifier + ", fomList=" + fomList + ", fBean=" + fBean + ", idFom=" + idFom + ", filedOperativeManager=" + filedOperativeManager + ", interview=" + interview + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", dob=" + dob + ", email=" + email + ", telephoneNumber=" + telephoneNumber + ", password=" + password + ", educationType=" + educationType + ", educationLevel=" + educationLevel + ", verifierVerificationStatus=" + verifierVerificationStatus + ", verifierVerificationComment=" + verifierVerificationComment + ", idAddress=" + idAddress + ", street=" + street + ", village=" + village + ", postcode=" + postcode + ", town=" + town + ", city=" + city + ", district=" + district + ", addressRegion=" + addressRegion + ", addressCountry=" + addressCountry + ", address=" + address + ", addressVerificationStatus=" + addressVerificationStatus + ", addressVerificationComment=" + addressVerificationComment + ", fileBi=" + fileBi + ", file=" + file + ", fileVerificationStatus=" + fileVerificationStatus + ", fileVerificationComment=" + fileVerificationComment + ", image=" + image + ", idReference1=" + idReference1 + ", reference1Title=" + reference1Title + ", reference1FullName=" + reference1FullName + ", reference1OrganisationName=" + reference1OrganisationName + ", reference1Designation=" + reference1Designation + ", reference1ContactNumber=" + reference1ContactNumber + ", reference1Email=" + reference1Email + ", reference1Address=" + reference1Address + ", reference1VerificationStatus=" + reference1VerificationStatus + ", reference1VerificationComment=" + reference1VerificationComment + ", idReference2=" + idReference2 + ", reference2Title=" + reference2Title + ", reference2FullName=" + reference2FullName + ", reference2OrganisationName=" + reference2OrganisationName + ", reference2Designation=" + reference2Designation + ", reference2ContactNumber=" + reference2ContactNumber + ", reference2Email=" + reference2Email + ", reference2Address=" + reference2Address + ", reference2VerificationStatus=" + reference2VerificationStatus + ", reference2VerificationComment=" + reference2VerificationComment + ", bankAccountNumber=" + bankAccountNumber + ", bankName=" + bankName + ", bankContactNumber=" + bankContactNumber + ", bankAddress=" + bankAddress + ", bankSortCode=" + bankSortCode + ", bankIban=" + bankIban + ", bankVerificationStatus=" + bankVerificationStatus + ", bankVerificationComment=" + bankVerificationComment + ", identityDocumentType=" + identityDocumentType + ", identityDocumentNumber=" + identityDocumentNumber + ", identityDocumentIssueDate=" + identityDocumentIssueDate + ", identityDocumentExpiryDate=" + identityDocumentExpiryDate + ", identityDocumentVerificationStatus=" + identityDocumentVerificationStatus + ", identityDocumentVerificationComment=" + identityDocumentVerificationComment + '}';
    }
}
