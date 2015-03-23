package org.haftrust.verifier.service;

import java.util.List;
import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.Device;
import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.Fom;
import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Interview;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.StaticData;
import org.haftrust.verifier.model.Verifier;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miroslav
 */
public interface VerifierService {

    public int preRegisterVerifier(String email, String password);

    public List<Country> getCountryList();

    public Country setVerifierCountry(int id);

    public List<Region> getRegionList(/*Country c*/);

    public Region setVerifierRegion(int idRegion);

    public List<District> getDistrictList();

    public District setVerifierDistrict(int idDistrict);

    public Verifier logInVerifier(String email, String password);

    public Verifier getVerifier();

    public Address getAddress();

    public Reference getReference2();

    public Bank getBank();

    public IdentityDocument getIdentityDocument();

    public Image getImage();

    public Reference getReference1();

    public List<StaticData> getGenderList();

    public List<StaticData> getEducationLevelList();

    public List<StaticData> getEducationTypeList();

    public void setVerifierDetails(String strFirstName,
            String strMiddleName,
            String strLastName,
            String strGender,
            java.sql.Date sqlDob,
            String strTelephoneNumber,
            String strEducationLevel,
            String strEducationType);

    public void setAddressDetails(String strStreet,
            String strVillage,
            String strPostcode,
            String strTown,
            String strgetCity);

    public List<StaticData> getIdentityDocumentTypeList();

    public void setImageDetails(MultipartFile mFile);

    public List<Verifier> isVerifier(String email, String password);

    public List<StaticData> getTitleList();

    public void setIdentityDocumentDetails(String strType,
            String strNumber,
            java.sql.Date sqlIssueDate,
            java.sql.Date sqlExpiryDate);

    public void setBankDetails(Bank bank);

    public void setReference1Details(String strReference1Title,
            String strReference1FullName,
            String strReference1OrganisationName,
            String strReference1Designation,
            String strReference1ContactNumber,
            String strReference1Email,
            String strReference1Address);

    public void setReference2Details(String strReference2Title,
            String strReference2FullName,
            String strReference2OrganisationName,
            String strReference2Designation,
            String strReference2ContactNumber,
            String strReference2Email,
            String strReference2Address);

    public Verifier registerVerifier();

    public void save(int page);

    public List<Verifier> getRegisteredVerifiers();

    public void getRegisteredVerifierDetails(int id);

    public District getVerifierDistrict();

    public Country getVerifierCountry();

    public Region getVerifierRegion();

    public List<StaticData> getVerificationStatusList();

    public Fom setVerifyVerifierDetils(String strVerifierVerificationStatus,
            String strVerifierVerificationComment,
            String strAddressVerificationStatus,
            String strAddressVerificationComment,
            String strImageVerificationStatus,
            String strImageVerificationComment,
            String strIdentityDocumentVerificationStatus,
            String strIdentityDocumentVerificationComment,
            String strBankVerificationStatus,
            String strBankVerificationComment,
            String strReference1VerificationStatus,
            String strReference1VerificationComment,
            String strReference2VerificationStatus,
            String strReference2VerificationComment,
            int idFom,
            boolean verified);

    public void saveVerifyVerifier();

    public List<Fom> getFoms();

    public Interview verifyVerifier();

    public boolean checkEmail(String email);

    public List<Verifier> getEmployedVerifiersWithoutDevice();

    public void getEmployedVerifierDetails(int id);

    public List<Device> getUnallocatedDeviceList();

    public Device setVerifierDevice(long imei);

    public void allocateDevice();

    public void failVerification(String strVerifierVerificationComment);

    public List<Bank> getBanksWhereAccountIsRegistered(String account);
}
