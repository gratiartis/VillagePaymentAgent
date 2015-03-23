package org.haftrust.verifier.controller;

import javax.validation.Valid;

import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.view.PreRegisterVerifierBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("preregisterverifier.htm")
public class PreRegisterVerifierController {

    private static final Logger LOG = LoggerFactory.getLogger(PreRegisterVerifierController.class);
    protected static final String FORM_JSP_PAGE = "preregisterverifier";
    protected static final String RESULT_JSP_PAGE = "preregisterverifierconfirmation";

    private final VerifierService verifierService;

    @Autowired
    public PreRegisterVerifierController(final VerifierService verifierService) {
        this.verifierService = verifierService;
    }

    @ModelAttribute("prvBean")
    public PreRegisterVerifierBean formBean() {
        return new PreRegisterVerifierBean();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initialisePage() {
        return FORM_JSP_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("prvBean") PreRegisterVerifierBean command, final BindingResult result) {

        if (result.hasErrors()) {
            return FORM_JSP_PAGE;
        }

        LOG.debug("------------------------ controller preregister verifier onSubmit");

        int id = verifierService.preRegisterVerifier(command.getEmail(), command.getPassword());

        command.setIdVerifier(id);

        return RESULT_JSP_PAGE;
    }
}
