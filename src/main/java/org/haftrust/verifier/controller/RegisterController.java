package org.haftrust.verifier.controller;

import org.haftrust.verifier.dao.CountryDAO;
import org.haftrust.verifier.dao.DistrictDAO;
import org.haftrust.verifier.dao.IdentityDocumentDAO;
import org.haftrust.verifier.dao.RegionDAO;
import org.haftrust.verifier.dao.VerifierDAO;
import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EducationLevel;
import org.haftrust.verifier.model.enums.EducationType;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.Gender;
import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.view.IdentityDocumentFormBean;
import org.haftrust.verifier.view.LoginFormBean;
import org.haftrust.verifier.view.ReferenceFormBean;
import org.haftrust.verifier.view.RegisterVerifierBean;
import org.haftrust.verifier.view.SelectCountryFormBean;
import org.haftrust.verifier.view.SelectDistrictFormBean;
import org.haftrust.verifier.view.SelectRegionFormBean;
import org.haftrust.verifier.view.VerifierDetailsFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;

import javax.validation.Valid;

@Controller
@RequestMapping("registerVerifier.htm")
@SessionAttributes({ "verifier", "rvBean" })
@Scope("session")
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    private final VerifierService verifierService;
    private final VerifierDAO verifierDao;
    private final CountryDAO countryDao;
    private final RegionDAO regionDao;
    private final DistrictDAO districtDao;
    private final IdentityDocumentDAO identityDocumentDao;
    
    private Integer verifierId;
    
    @Autowired
    public RegisterController(final VerifierService verifierService,
                              final VerifierDAO verifierDao,
                              final CountryDAO countryDao,
                              final RegionDAO regionDao,
                              final DistrictDAO districtDao,
                              final IdentityDocumentDAO identityDocumentDao) {
        this.verifierService = verifierService;
        this.verifierDao = verifierDao;
        this.countryDao = countryDao;
        this.regionDao = regionDao;
        this.districtDao = districtDao;
        this.identityDocumentDao = identityDocumentDao;
    }

    @ModelAttribute("loginFormBean")
    public LoginFormBean loginFormBean() {
        return new LoginFormBean();
    }
    
    @ModelAttribute("selectCountryFormBean")
    public SelectCountryFormBean selectCountryFormBean() {
        Verifier verifier = verifierDao.findOne(verifierId);
        if (verifier.getAddress() == null) {
            return new SelectCountryFormBean();
        } else {
            return new SelectCountryFormBean(verifier.getAddress().getCountry().getId());
        }
    }
    
    @ModelAttribute("selectRegionFormBean")
    public SelectRegionFormBean selectRegionFormBean() {
        Verifier verifier = verifierDao.findOne(verifierId);
        if (verifier.getAddress() == null) {
            return new SelectRegionFormBean();
        } else {
            return new SelectRegionFormBean(verifier.getAddress().getRegion().getId());
        }
    }
    
    @ModelAttribute("selectDistrictFormBean")
    public SelectDistrictFormBean selectDistrictFormBean() {
        Verifier verifier = verifierDao.findOne(verifierId);
        if (verifier.getAddress() == null) {
            return new SelectDistrictFormBean();
        } else {
            return new SelectDistrictFormBean(verifier.getAddress().getDistrict().getId());
        }
    }
    
    @ModelAttribute("verifierDetailsFormBean")
    public VerifierDetailsFormBean verifierDetailsFormBean() {
        Verifier verifier = verifierDao.findOne(verifierId);
        return new VerifierDetailsFormBean(verifier);
    }
    
    @ModelAttribute("identityDocumentFormBean")
    public IdentityDocumentFormBean identityDocumentFormBean() {
        Verifier verifier = verifierDao.findOne(verifierId);
        if (verifier.getIdentity() == null) {
            return new IdentityDocumentFormBean();
        } else {
            return new IdentityDocumentFormBean(verifier.getIdentity());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayLoginForm(
            @ModelAttribute("loginFormBean") LoginFormBean loginFormBean) {
        
        return "logIn";
    }

    @RequestMapping(method = RequestMethod.POST, params = "login")
    public ModelAndView loginAndDisplayCountrySelection(
            @Valid @ModelAttribute("loginFormBean") LoginFormBean loginFormBean,
            BindingResult result,
            @ModelAttribute("selectCountryFormBean") SelectCountryFormBean selectCountryFormBean) {

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
        
        // Set the verifier, which is session scoped, so we can track the current user.
        // TODO Remove when Spring Security implemented, as we can get current user from context.
        this.verifierId = verifier.getId();

        return displayCountrySelection(selectCountryFormBean);
    }
    
    public ModelAndView displayCountrySelection(
            @ModelAttribute("selectCountryFormBean") SelectCountryFormBean selectCountryFormBean) {
        
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("countryList", countryDao.findAll());
        
        return new ModelAndView("selectCountry", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "selectCountry")
    public ModelAndView selectCountry(
            @Valid @ModelAttribute("selectCountryFormBean") SelectCountryFormBean form,
            final BindingResult result) {
        
        if (result.hasErrors()) {
            return displayCountrySelection(form);
        }

        Country country = countryDao.findOne(form.getCountryId());
        
        if (country == null) {
            result.reject("validation.data.country.notfound", "The country could not be found.");
            return displayCountrySelection(form);
        }
        
        Verifier verifier = verifierDao.findOne(verifierId);
        this.verifierService.updateVerifierCountry(verifier, country);

        return displayRegionSelectionForm(selectRegionFormBean(), result);
    }
    
    public ModelAndView displayRegionSelectionForm(
            @ModelAttribute("selectRegionFormBean") SelectRegionFormBean selectRegionFormBean,
            BindingResult result) {
        
        Verifier verifier = verifierDao.findOne(verifierId);
        Country country = verifier.getAddress().getCountry();
        
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("regionList", regionDao.findByCountry(country));

        return new ModelAndView("selectRegion", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "selectRegion")
    public ModelAndView selectRegion(
            @Valid @ModelAttribute("selectRegionFormBean") SelectRegionFormBean form,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return displayRegionSelectionForm(form, result);
        }
        
        Region region = regionDao.findOne(form.getRegionId());
        
        if (region == null) {
            result.reject("validation.data.region.notfound", "The region could not be found.");
            return displayRegionSelectionForm(form, result);
        }
        
        Verifier verifier = verifierDao.findOne(verifierId);
        this.verifierService.updateVerifierRegion(verifier, region);

        return displayDistrictSelectionForm(selectDistrictFormBean());
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "selectRegion")
    public ModelAndView displayDistrictSelectionForm(
            @ModelAttribute("selectDistrictFormBean") SelectDistrictFormBean form) {
        
        Verifier verifier = verifierDao.findOne(verifierId);
        
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("districtList", districtDao.findByRegion(verifier.getAddress().getRegion()));

        return new ModelAndView("selectDistrict", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "selectDistrict")
    public ModelAndView updateDistrictAndDisplayVerifierDetailsForm(
            @Valid @ModelAttribute("selectDistrictFormBean") SelectDistrictFormBean form,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return displayDistrictSelectionForm(form);
        }
        
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("genderList", this.verifierService.getGenderList());
        dataMap.put("educationLevelList", this.verifierService.getEducationLevelList());
        dataMap.put("educationTypeList", this.verifierService.getEducationTypeList());

        return new ModelAndView("createVerifier", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "createVerifier")
    public ModelAndView updateVerifierDetailsAndDisplayIdentityDocumentForm(
            @ModelAttribute("verifierDetailsFormBean") VerifierDetailsFormBean form,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return new ModelAndView("createVerifier");
        }

        Verifier verifier = verifierDao.findOne(this.verifierId);
        verifier.setFirstName(form.getFirstName());
        verifier.setMiddleName(form.getMiddleName());
        verifier.setLastName(form.getLastName());
        verifier.setGender(Gender.valueOfValue(form.getGender()));
        verifier.setTelephoneNumber(form.getTelephoneNumber());
        verifier.setDob(LocalDate.parse(form.getDob()));
        verifier.setEducationLevel(EducationLevel.valueOfValue(form.getEducationLevel()));
        verifier.setEducationType(EducationType.valueOfValue(form.getEducationType()));
        verifierService.updateVerifierDetails(verifier);
        
        LOG.debug("Updated verifier details: " + verifier);
        
        Address address = verifier.getAddress(); 
        address.setStreet(form.getStreet());
        address.setVillage(form.getVillage());
        address.setTown(form.getTown());
        address.setCity(form.getCity());
        address.setPostcode(form.getPostcode());
        
        LOG.debug("Updated verifier address: " + address);
        
        verifierService.updateVerifierAddress(verifier, address);
        
        MultipartFile file = form.getFile();
        if (file == null) {
            LOG.debug("--------------------------- register verifier controller file is empty");
        } else {
            LOG.debug("--------------------------- register verifier controller file: {}", file);
        }
        verifierService.updateVerifierImageDetails(verifier, form.getImage());

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("identityDocumentTypeList", this.verifierService.getIdentityDocumentTypeList());

        return displayIdentityDocumentForm(verifierDetailsFormBean());
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "createVerifier")
    public ModelAndView displayIdentityDocumentForm(
            @ModelAttribute("verifierDetailsFormBean") VerifierDetailsFormBean form) {
        
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("identityDocumentTypeList", identityDocumentDao.findAll());

        return new ModelAndView("createIdentityDocument", dataMap);
    }

    @RequestMapping(method = RequestMethod.POST, params = "createIdentityDocument")
    public String createBank(
            @ModelAttribute("identityDocumentFormBean") IdentityDocumentFormBean form, 
            BindingResult result) {

        if (result.hasErrors()) {
            return "createIdentityDocument";
        }

        IdentityDocument identity = new IdentityDocument(); 
        identity.setEmployeeType(EmployeeType.VERIFIER);
        identity.setExpiryDate(LocalDate.parse(form.getExpiryDate()));
        identity.setIssueDate(LocalDate.parse(form.getIssueDate()));

        Verifier verifier = verifierDao.findOne(this.verifierId);
        verifierService.updateVerifierIdentityDocument(verifier, identity);

        return "createBank";
    }

    @RequestMapping(method = RequestMethod.POST, params = "createBank")
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

    @RequestMapping(method = RequestMethod.POST, params = "createReference1")
    public ModelAndView updateReference1AndDisplayReference2Form(
            @Valid @ModelAttribute("referenceFormBean") ReferenceFormBean form, 
            BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("createReference");
        }
        
        Reference ref = new Reference();
        
        ref.setAddress(form.getAddress());
        ref.setContactNumber(form.getContactNumber());
        ref.setDesignation(form.getDesignation());
        ref.setEmail(form.getEmail());
        ref.setFullName(form.getFullName());
        ref.setOrganisationName(form.getOrganisationName());
        ref.setTitle(form.getTitle());
        
        this.verifierService.setReference1Details(verifier, ref);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("titleList", this.verifierService.getTitleList());

        return new ModelAndView("createReference2", dataMap);
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "createReference2")
    public String finish(
            @Valid @ModelAttribute("referenceFormBean") ReferenceFormBean form,
            BindingResult result,
            SessionStatus sessionStatus) {

        if (result.hasErrors()) {
            return "createReference2";
        }

        Reference ref = new Reference();
        
        ref.setAddress(form.getAddress());
        ref.setContactNumber(form.getContactNumber());
        ref.setDesignation(form.getDesignation());
        ref.setEmail(form.getEmail());
        ref.setFullName(form.getFullName());
        ref.setOrganisationName(form.getOrganisationName());
        ref.setTitle(form.getTitle());
        
        this.verifierService.setReference2Details(verifier, ref);

        LOG.info("finish called");
        sessionStatus.setComplete();

        return "registerConfirmation";
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
