package org.haftrust.verifier.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.view.AllocateDeviceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("allocateDevice.htm")
@SessionAttributes("adBean")
public class AllocateDeviceController {

    private final VerifierService verifierService;

    @Autowired
    public AllocateDeviceController(final VerifierService verifierService) {
        this.verifierService = verifierService;
    }

    @ModelAttribute("adBean")
    public AllocateDeviceBean formBean() {
        return new AllocateDeviceBean();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView selectCountry(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {
        return new ModelAndView("selectAllocateDeviceCountry", "countryList", this.verifierService.getCountryList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target0")
    public ModelAndView backToCountry(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {
        return selectCountry(adBean);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target1")
    public ModelAndView selectRegion(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {
        adBean.setCountry(this.verifierService.setVerifierCountry(adBean.getIdCountry()));

        return new ModelAndView("searchAllocateDeviceVerifier", "regionList", this.verifierService.getRegionList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target2")
    public ModelAndView selectVerifier(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {

        adBean.setRegion(this.verifierService.setVerifierRegion(adBean.getIdRegion()));

        List<Verifier> v = this.verifierService.getEmployedVerifiersWithoutDevice();

        if (v.isEmpty()) {
            return new ModelAndView("noVerifierFound");
        }

        adBean.setVerifierList(v);
        adBean.setvBean(v);

        return new ModelAndView("selectAllocateDeviceVerifier", "regionList", this.verifierService.getRegionList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target3")
    public ModelAndView selectMobile(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {

        this.verifierService.getEmployedVerifierDetails(adBean.getIdVerifier());

        Verifier verifier = this.verifierService.getVerifier();
        adBean.setFirstName(verifier.getFirstName());
        adBean.setMiddleName(verifier.getMiddleName());
        adBean.setLastName(verifier.getLastName());
        if (verifier.getDob() != null) {
            LocalDate date = verifier.getDob();
            adBean.setDob(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        adBean.setEmail(verifier.getEmail());

        return new ModelAndView("allocateDeviceVerifier", "unallocatedDeviceList", this.verifierService.getUnallocatedDeviceList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_cancel")
    public String processCancel() {
        return "cancelAllocateDeviceConfirmation";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_finish")
    public String processFinish(@ModelAttribute("adBean") final AllocateDeviceBean adBean) {
        adBean.setDevice(this.verifierService.setVerifierDevice(adBean.getImei()));

        this.verifierService.allocateDevice();

        return "allocateDeviceConfirmation";
    }
}
