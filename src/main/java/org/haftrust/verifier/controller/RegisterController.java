
package org.haftrust.verifier.controller;

import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.validator.IdentityDocumentValidator;
import org.haftrust.verifier.validator.LogInValidator;
import org.haftrust.verifier.validator.Reference1Validator;
import org.haftrust.verifier.validator.Reference2Validator;
import org.haftrust.verifier.validator.SelectCountryValidator;
import org.haftrust.verifier.validator.VerifierValidator;
import org.haftrust.verifier.view.LoginFormBean;
import org.haftrust.verifier.view.RegisterVerifierBean;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import javax.validation.Valid;

@Controller
@RequestMapping("registerVerifier.htm")
@SessionAttributes("rvBean")
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    private final VerifierService verifierService;
    private final LogInValidator logInValidator;
    private final SelectCountryValidator selectCountryValidator;
    private final Reference1Validator reference1Validator;
    private final VerifierValidator verifierValidator;
    private final IdentityDocumentValidator identityDocumentValidator;
    private final Reference2Validator reference2Validator;

    @Autowired
    public RegisterController(final VerifierService verifierService,
                              final LogInValidator logInValidator,
                              final SelectCountryValidator selectCountryValidator,
                              final Reference1Validator reference1Validator,
                              final VerifierValidator verifierValidator,
                              final IdentityDocumentValidator identityDocumentValidator,
                              final Reference2Validator reference2Validator) {
        this.verifierService = verifierService;
        this.logInValidator = logInValidator;
        this.selectCountryValidator = selectCountryValidator;
        this.reference1Validator = reference1Validator;
        this.verifierValidator = verifierValidator;
        this.identityDocumentValidator = identityDocumentValidator;
        this.reference2Validator = reference2Validator;
    }

    @ModelAttribute("loginFormBean")
    public LoginFormBean loginFormBean() {
        return new LoginFormBean();
    }
    
    @ModelAttribute("rvBean")
    public RegisterVerifierBean formBean() {
        return new RegisterVerifierBean();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayLoginForm(@ModelAttribute("loginFormBean") LoginFormBean loginFormBean) {
        return "logIn";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target1")
    public ModelAndView loginAndDisplayCountrySelection(
            @Valid @ModelAttribute("loginFormBean") LoginFormBean loginFormBean,
            final BindingResult result) {

        if (result.hasErrors()) {
            LOG.debug("Login form failed validation: " + loginFormBean);
            return new ModelAndView("logIn");
        }
        
        Verifier verifier = this.verifierService.logInVerifier(loginFormBean.getEmail(), loginFormBean.getPassword());
        
        if (verifier == null) {
            LOG.debug("Verifier was not found for: " + loginFormBean);
            result.reject("validation.constraints.auth.login", "The Email or Password entered is incorrect.");
        }
        
        if (result.hasErrors()) {
            return new ModelAndView("logIn");
        }

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("countryList", this.verifierService.getCountryList());
        LOG.debug("------------------------ controller register verifier reference data country list");

        RegisterVerifierBean rvBean = formBean();
        dataMap.put("rvBean", rvBean);
        
        rvBean.setVerifier(verifier);
        rvBean.setFirstName(verifier.getFirstName());
        LOG.debug("----------------------- rvBean frst name: {}", rvBean.getFirstName());
        rvBean.setMiddleName(verifier.getMiddleName());
        rvBean.setLastName(verifier.getLastName());
        rvBean.setGender(verifier.getGender());
        if (verifier.getDob() != null) {
            String date = verifier.getDob().toString();
            String[] strSplit = date.split("-");
            int day = Integer.parseInt(strSplit[2]);
            int month = Integer.parseInt(strSplit[1]);
            int year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            rvBean.setDob(date);
        }
        rvBean.setEmail(verifier.getEmail());
        LOG.debug("--------------------register verifier controller post process page verifier email: {}", rvBean.getEmail());
        rvBean.setTelephoneNumber(verifier.getTelephoneNumber());
        rvBean.setEducationType(verifier.getEducationType() == null ? null : verifier.getEducationType().value);
        rvBean.setEducationLevel(verifier.getEducationLevel() == null ? null : verifier.getEducationLevel().value);
        rvBean.setIdVerifier(verifier.getId());
        if (verifier.getImage() != null) {
            rvBean.setImage(verifier.getImage());
        }
        LOG.debug("---------------- login verifier id: {}" + rvBean.getVerifier().getId());

        Address address = this.verifierService.getAddress();
        rvBean.setStreet(address.getStreet());
        LOG.debug("----------------------- rvBean street: {}", rvBean.getStreet());
        rvBean.setVillage(address.getVillage());
        rvBean.setPostcode(address.getPostcode());
        rvBean.setTown(address.getTown());
        rvBean.setCity(address.getCity());
        if (address.getDistrict() != null) {
            rvBean.setDistrict(address.getDistrict());
            rvBean.setIdDistrict(address.getDistrict().getId());
        }
        if (address.getRegion() != null) {
            rvBean.setRegion(address.getRegion());
            rvBean.setIdRegion(address.getRegion().getId());
        }
        if (address.getCountry() != null) {
            rvBean.setCountry(address.getCountry());
            rvBean.setIdCountry(address.getCountry().getId());
        }

        Bank bank = this.verifierService.getBank();
        rvBean.setBankAccountNumber(bank.getAccountNumber());
        rvBean.setBankName(bank.getBankName());
        LOG.debug("----------------------- rvBean bank name: {}", rvBean.getBankName());
        rvBean.setBankContactNumber(bank.getContactNumber());
        rvBean.setBankAddress(bank.getAddress());
        rvBean.setBankSortCode(bank.getSortcode());
        rvBean.setBankIban(bank.getIban());

        Reference reference1 = this.verifierService.getReference1();
        rvBean.setReference1Title(reference1.getTitle());
        rvBean.setReference1FullName(reference1.getFullName());
        LOG.debug("----------------------- rvBean reference 1 full name: {}", rvBean.getReference1FullName());
        rvBean.setReference1OrganisationName(reference1.getOrganisationName());
        rvBean.setReference1Designation(reference1.getDesignation());
        rvBean.setReference1ContactNumber(reference1.getContactNumber());
        rvBean.setReference1Email(reference1.getEmail());
        rvBean.setReference1Address(reference1.getAddress());

        Reference reference2 = this.verifierService.getReference2();
        rvBean.setReference2Title(reference2.getTitle());
        rvBean.setReference2FullName(reference2.getFullName());
        LOG.debug("----------------------- rvBean reference 2 full name: {}", rvBean.getReference2FullName());
        rvBean.setReference2OrganisationName(reference2.getOrganisationName());
        rvBean.setReference2Designation(reference2.getDesignation());
        rvBean.setReference2ContactNumber(reference2.getContactNumber());
        rvBean.setReference2Email(reference2.getEmail());
        rvBean.setReference2Address(reference2.getAddress());

        IdentityDocument id = this.verifierService.getIdentityDocument();
        rvBean.setIdentityDocumentType(id.getType() == null ? null : id.getType().value);
        rvBean.setIdentityDocumentNumber(id.getNumber());
        if (id.getIssueDate() != null) {
            String date;
            date = id.getIssueDate().toString();
            String[] strSplit = date.split("-");
            int day = Integer.parseInt(strSplit[2]);
            int month = Integer.parseInt(strSplit[1]);
            int year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            rvBean.setIdentityDocumentIssueDate(date);
        }
        if (id.getExpiryDate() != null) {
            String date;
            date = id.getExpiryDate().toString();
            String[] strSplit = date.split("-");
            int day = Integer.parseInt(strSplit[2]);
            int month = Integer.parseInt(strSplit[1]);
            int year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            rvBean.setIdentityDocumentExpiryDate(date);
        }

        return new ModelAndView("selectCountry", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target2")
    public ModelAndView regionSelection(@ModelAttribute("rvBean") RegisterVerifierBean rvBean,
                                        final BindingResult result) {
        selectCountryValidator.validate(rvBean, result);

        if (result.hasErrors()) {
            return new ModelAndView("logIn");
        }

        rvBean.setCountry(this.verifierService.setVerifierCountry(rvBean.getIdCountry()));

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("regionList", this.verifierService.getRegionList());

        return new ModelAndView("selectRegion", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target3")
    public ModelAndView districtSelection(@ModelAttribute("rvBean") RegisterVerifierBean rvBean) {
        rvBean.setRegion(this.verifierService.setVerifierRegion(rvBean.getIdRegion()));

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("districtList", this.verifierService.getDistrictList());

        return new ModelAndView("selectDistrict", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target4")
    public ModelAndView createVerifier(@ModelAttribute("rvBean") RegisterVerifierBean rvBean) {
        rvBean.setDistrict(this.verifierService.setVerifierDistrict(rvBean.getIdDistrict()));
        rvBean.setPage(4);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("genderList", this.verifierService.getGenderList());
        dataMap.put("educationLevelList", this.verifierService.getEducationLevelList());
        dataMap.put("educationTypeList", this.verifierService.getEducationTypeList());

        return new ModelAndView("createVerifier", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target5")
    public ModelAndView createIdentityDocument(@ModelAttribute("rvBean") RegisterVerifierBean rvBean,
                                               BindingResult result) {
        verifierValidator.validate(rvBean, result);

        if (result.hasErrors()) {
            return new ModelAndView("createVerifier");
        }

        this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                rvBean.getMiddleName(),
                rvBean.getLastName(),
                rvBean.getGender(),
                rvBean.getSqlDob(),
                rvBean.getTelephoneNumber(),
                rvBean.getEducationLevel(),
                rvBean.getEducationType());

        this.verifierService.setAddressDetails(rvBean.getStreet(),
                rvBean.getVillage(),
                rvBean.getPostcode(),
                rvBean.getTown(),
                rvBean.getCity());

        MultipartFile file = rvBean.getFile();
        if (file == null) {
            LOG.debug("--------------------------- register verifier controller file is empty");
        } else {
            LOG.debug("--------------------------- register verifier controller file: {}", file);
        }
        this.verifierService.setImageDetails(rvBean.getFile());
        rvBean.setImage(this.verifierService.getImage());
        rvBean.setPage(5);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("identityDocumentTypeList", this.verifierService.getIdentityDocumentTypeList());

        return new ModelAndView("createIdentityDocument", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target6")
    public String createBank(@ModelAttribute("rvBean") RegisterVerifierBean rvBean, final BindingResult result) {
        identityDocumentValidator.validate(rvBean, result);

        if (result.hasErrors()) {
            return "createIdentityDocument";
        }

        this.verifierService.setIdentityDocumentDetails(rvBean.getIdentityDocumentType(),
                rvBean.getIdentityDocumentNumber(),
                rvBean.getSqlIdentityDocumentIssueDate(),
                rvBean.getSqlIdentityDocumentExpiryDate());
        rvBean.setPage(6);

        return "createBank";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target7")
    public ModelAndView createBankAndDisplayCreateReferenceForm(
            @Valid @ModelAttribute("rvBean") RegisterVerifierBean rvBean, 
            final BindingResult result) {
        
        if (result.hasErrors()) {
            LOG.debug("Create bank form failed validation: " + rvBean);
            return new ModelAndView("createBank");
        }

        this.verifierService.setBankDetails(bank(rvBean));
        rvBean.setPage(7);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("titleList", this.verifierService.getTitleList());

        return new ModelAndView("createReference", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target10")
    public ModelAndView createReference2(@ModelAttribute("rvBean") RegisterVerifierBean rvBean, final BindingResult result) {
        reference1Validator.validate(rvBean, result);

        if (result.hasErrors()) {
            return new ModelAndView("createReference");
        }

        this.verifierService.setReference1Details(rvBean.getReference1Title(),
                rvBean.getReference1FullName(),
                rvBean.getReference1OrganisationName(),
                rvBean.getReference1Designation(),
                rvBean.getReference1ContactNumber(),
                rvBean.getReference1Email(),
                rvBean.getReference1Address());
        rvBean.setPage(10);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("titleList", this.verifierService.getTitleList());

        return new ModelAndView("createReference2", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target11")
    public String saveRegister(@ModelAttribute("rvBean") RegisterVerifierBean rvBean) {

        if (rvBean.getPage() == 4) {
            rvBean.setTarget("_target4");
        }

        if (rvBean.getPage() == 5) {
            rvBean.setTarget("_target5");
        }

        if (rvBean.getPage() == 6) {
            rvBean.setTarget("_target6");
        }

        if (rvBean.getPage() == 7) {
            rvBean.setTarget("_target7");
        }

        if (rvBean.getPage() == 10) {
            rvBean.setTarget("_target10");
        }

        return "saveRegister";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target8")
    public String saveConfirmation(@ModelAttribute("rvBean") RegisterVerifierBean rvBean,
                                   SessionStatus sessionStatus) {

        if (rvBean.getPage() == 4) {
            this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                    rvBean.getMiddleName(),
                    rvBean.getLastName(),
                    rvBean.getGender(),
                    rvBean.getSqlDob(),
                    rvBean.getTelephoneNumber(),
                    rvBean.getEducationLevel(),
                    rvBean.getEducationType());

            this.verifierService.setAddressDetails(rvBean.getStreet(),
                    rvBean.getVillage(),
                    rvBean.getPostcode(),
                    rvBean.getTown(),
                    rvBean.getCity());

            this.verifierService.setImageDetails(rvBean.getFile());

            this.verifierService.save(rvBean.getPage());

            rvBean.setImage(this.verifierService.getImage());
            rvBean.setVerifier(this.verifierService.getVerifier());
            rvBean.setAddress(this.verifierService.getAddress());
        }

        if (rvBean.getPage() == 5) {
            this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                    rvBean.getMiddleName(),
                    rvBean.getLastName(),
                    rvBean.getGender(),
                    rvBean.getSqlDob(),
                    rvBean.getTelephoneNumber(),
                    rvBean.getEducationLevel(),
                    rvBean.getEducationType());

            this.verifierService.setAddressDetails(rvBean.getStreet(),
                    rvBean.getVillage(),
                    rvBean.getPostcode(),
                    rvBean.getTown(),
                    rvBean.getCity());

            this.verifierService.setImageDetails(rvBean.getFile());

            this.verifierService.setIdentityDocumentDetails(rvBean.getIdentityDocumentType(),
                    rvBean.getIdentityDocumentNumber(),
                    rvBean.getSqlIdentityDocumentIssueDate(),
                    rvBean.getSqlIdentityDocumentExpiryDate());

            this.verifierService.save(rvBean.getPage());

            rvBean.setImage(this.verifierService.getImage());
            rvBean.setVerifier(this.verifierService.getVerifier());
            rvBean.setAddress(this.verifierService.getAddress());
            rvBean.setIdentityDocument(this.verifierService.getIdentityDocument());
        }

        if (rvBean.getPage() == 6) {
            this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                    rvBean.getMiddleName(),
                    rvBean.getLastName(),
                    rvBean.getGender(),
                    rvBean.getSqlDob(),
                    rvBean.getTelephoneNumber(),
                    rvBean.getEducationLevel(),
                    rvBean.getEducationType());

            this.verifierService.setAddressDetails(rvBean.getStreet(),
                    rvBean.getVillage(),
                    rvBean.getPostcode(),
                    rvBean.getTown(),
                    rvBean.getCity());

            this.verifierService.setImageDetails(rvBean.getFile());

            this.verifierService.setIdentityDocumentDetails(rvBean.getIdentityDocumentType(),
                    rvBean.getIdentityDocumentNumber(),
                    rvBean.getSqlIdentityDocumentIssueDate(),
                    rvBean.getSqlIdentityDocumentExpiryDate());

            this.verifierService.setBankDetails(bank(rvBean));

            this.verifierService.save(rvBean.getPage());

            rvBean.setImage(this.verifierService.getImage());
            rvBean.setVerifier(this.verifierService.getVerifier());
            rvBean.setAddress(this.verifierService.getAddress());
            rvBean.setIdentityDocument(this.verifierService.getIdentityDocument());
            rvBean.setBank(this.verifierService.getBank());
        }

        if (rvBean.getPage() == 7) {
            this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                    rvBean.getMiddleName(),
                    rvBean.getLastName(),
                    rvBean.getGender(),
                    rvBean.getSqlDob(),
                    rvBean.getTelephoneNumber(),
                    rvBean.getEducationLevel(),
                    rvBean.getEducationType());

            this.verifierService.setAddressDetails(rvBean.getStreet(),
                    rvBean.getVillage(),
                    rvBean.getPostcode(),
                    rvBean.getTown(),
                    rvBean.getCity());

            this.verifierService.setImageDetails(rvBean.getFile());

            this.verifierService.setIdentityDocumentDetails(rvBean.getIdentityDocumentType(),
                    rvBean.getIdentityDocumentNumber(),
                    rvBean.getSqlIdentityDocumentIssueDate(),
                    rvBean.getSqlIdentityDocumentExpiryDate());

            this.verifierService.setBankDetails(bank(rvBean));

            this.verifierService.setReference1Details(rvBean.getReference1Title(),
                    rvBean.getReference1FullName(),
                    rvBean.getReference1OrganisationName(),
                    rvBean.getReference1Designation(),
                    rvBean.getReference1ContactNumber(),
                    rvBean.getReference1Email(),
                    rvBean.getReference1Address());

            this.verifierService.save(rvBean.getPage());

            rvBean.setImage(this.verifierService.getImage());
            rvBean.setVerifier(this.verifierService.getVerifier());
            rvBean.setAddress(this.verifierService.getAddress());
            rvBean.setIdentityDocument(this.verifierService.getIdentityDocument());
            rvBean.setBank(this.verifierService.getBank());
            rvBean.setReference1(this.verifierService.getReference1());
        }

        if (rvBean.getPage() == 10) {
            this.verifierService.setVerifierDetails(rvBean.getFirstName(),
                    rvBean.getMiddleName(),
                    rvBean.getLastName(),
                    rvBean.getGender(),
                    rvBean.getSqlDob(),
                    rvBean.getTelephoneNumber(),
                    rvBean.getEducationLevel(),
                    rvBean.getEducationType());

            this.verifierService.setAddressDetails(rvBean.getStreet(),
                    rvBean.getVillage(),
                    rvBean.getPostcode(),
                    rvBean.getTown(),
                    rvBean.getCity());

            this.verifierService.setImageDetails(rvBean.getFile());

            this.verifierService.setIdentityDocumentDetails(rvBean.getIdentityDocumentType(),
                    rvBean.getIdentityDocumentNumber(),
                    rvBean.getSqlIdentityDocumentIssueDate(),
                    rvBean.getSqlIdentityDocumentExpiryDate());

            this.verifierService.setBankDetails(bank(rvBean));

            this.verifierService.setReference1Details(rvBean.getReference1Title(),
                    rvBean.getReference1FullName(),
                    rvBean.getReference1OrganisationName(),
                    rvBean.getReference1Designation(),
                    rvBean.getReference1ContactNumber(),
                    rvBean.getReference1Email(),
                    rvBean.getReference1Address());

            this.verifierService.setReference2Details(rvBean.getReference2Title(),
                    rvBean.getReference2FullName(),
                    rvBean.getReference2OrganisationName(),
                    rvBean.getReference2Designation(),
                    rvBean.getReference2ContactNumber(),
                    rvBean.getReference2Email(),
                    rvBean.getReference2Address());

            this.verifierService.save(rvBean.getPage());

            rvBean.setImage(this.verifierService.getImage());
            rvBean.setVerifier(this.verifierService.getVerifier());
            rvBean.setAddress(this.verifierService.getAddress());
            rvBean.setIdentityDocument(this.verifierService.getIdentityDocument());
            rvBean.setBank(this.verifierService.getBank());
            rvBean.setReference1(this.verifierService.getReference1());
            rvBean.setReference2(this.verifierService.getReference2());
        }
        sessionStatus.setComplete();

        return "saveConfirmation";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target9")
    public String cancelRegister(@ModelAttribute("rvBean") RegisterVerifierBean rvBean) {

        // cancel registration select country page
        if (rvBean.getPage() == 1) {
            rvBean.setTarget("_target1");
        }

        // cancel registration select region page
        if (rvBean.getPage() == 2) {
            rvBean.setTarget("_target2");
        }

        // cancel registration select district page
        if (rvBean.getPage() == 3) {
            rvBean.setTarget("_target3");
        }

        // cancel registration create verifier page
        if (rvBean.getPage() == 4) {
            rvBean.setTarget("_target4");
        }

        // cancel registration create identity document page
        if (rvBean.getPage() == 5) {
            rvBean.setTarget("_target5");
        }

        // cancel registration create bank page
        if (rvBean.getPage() == 6) {
            rvBean.setTarget("_target6");
        }

        // cancel registration create reference page
        if (rvBean.getPage() == 7) {
            rvBean.setTarget("_target7");
        }

        return "cancelRegister";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_finish")
    public String finish(@ModelAttribute("rvBean") RegisterVerifierBean rvBean,
                         SessionStatus sessionStatus,
                         BindingResult result) {
        reference2Validator.validate(rvBean, result);

        if (result.hasErrors()) {
            return "createReference2";
        }

        this.verifierService.setReference2Details(rvBean.getReference2Title(),
                rvBean.getReference2FullName(),
                rvBean.getReference2OrganisationName(),
                rvBean.getReference2Designation(),
                rvBean.getReference2ContactNumber(),
                rvBean.getReference2Email(),
                rvBean.getReference2Address());

        Verifier v = this.verifierService.registerVerifier();

        rvBean.setImage(this.verifierService.getImage());
        rvBean.setVerifier(v);
        rvBean.setAddress(this.verifierService.getAddress());
        rvBean.setIdentityDocument(this.verifierService.getIdentityDocument());
        rvBean.setBank(this.verifierService.getBank());
        rvBean.setReference1(this.verifierService.getReference1());
        rvBean.setReference2(this.verifierService.getReference2());

        LOG.info("finish called");
        sessionStatus.setComplete();

        return "registerConfirmation";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_cancel")
    public String cancel(final SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "cancelConfirmation";
    }
    
    private Bank bank(RegisterVerifierBean rvBean) {
        Bank bank = new Bank();
        bank.setAccountNumber(rvBean.getBankAccountNumber());
        bank.setAddress(rvBean.getBankAddress());
        bank.setBankName(rvBean.getBankName());
        bank.setContactNumber(rvBean.getBankContactNumber());
        bank.setEmployeeType(null);
        bank.setIban(rvBean.getBankIban());
        bank.setSortcode(rvBean.getBankSortCode());
        return bank;
    }
}
